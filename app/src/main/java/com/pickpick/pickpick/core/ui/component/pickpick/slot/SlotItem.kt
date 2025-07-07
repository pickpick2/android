package com.pickpick.pickpick.core.ui.component.pickpick.slot

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.pickpick.SelectedUser
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.PrimaryLight
import com.pickpick.pickpick.core.ui.theme.SecondaryDefault
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.pick.takepicture.ui.CameraPreview
import com.pickpick.pickpick.presentation.pick.takepicture.ui.capturePhoto
import kotlinx.coroutines.launch


@Composable
fun SlotItem(
    modifier: Modifier = Modifier,
    slotType: SlotType,
    onSlotAction: (SlotAction) -> Unit,
) {
    when (slotType) {
        is SlotType.Position -> PositionSlotItem(
            modifier = modifier, slotData = slotType, onSelect = { index ->
                onSlotAction(SlotAction.SelectPosition(index))
            })

        is SlotType.Camera -> CameraSlot(
            rootModifier = modifier, slotData = slotType,
            onCapture = { index, uri ->
                onSlotAction(SlotAction.CapturePhoto(uri))
            },
        )

    }
}


@Composable
fun PositionSlotItem(
    modifier: Modifier = Modifier,
    slotData: SlotType.Position,
    onSelect: (Int) -> Unit,
) {
    Box {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickableNoRipple {
                    onSelect(slotData.index)
                }
                .border(
                    width = 1.dp,
                    color = if (slotData.isSelected) PrimaryLight else Border,
                    shape = RoundedCornerShape(5.dp)
                )) {
            Text(text = slotData.item.toString(), style = Heading1)
        }
        if (slotData.isSelected) Box(
            modifier = modifier
                .align(if (slotData.index % 2 == 0) Alignment.TopStart else Alignment.TopEnd)
                .offset(
                    x = if (slotData.index % 2 == 0) -15.dp else 15.dp, y = -15.dp
                )
        ) {
            SelectedUser(
                imageUrl = "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraSlot(
    modifier: Modifier = Modifier,
    rootModifier: Modifier = Modifier,
    slotData: SlotType.Camera,
    onCapture: (Int, Uri) -> Unit = { _, _ -> },
    onPermissionRequest: () -> Unit = {}
) {
    // 카메라 권한 체크
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    ) { granted ->
        if (!granted) {
            onPermissionRequest()
        }
    }

    // 카메라 및 촬영 상태
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var isCapturing by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(modifier = rootModifier) {
        Box(
            modifier = modifier
                .matchParentSize()
                .border(
                    width = 1.dp,
                    color = if (slotData.isSelected) PrimaryLight else Border,
                    shape = RoundedCornerShape(5.dp)
                )
                .clip(shape = RoundedCornerShape(5.dp))
        ) {

            // todo 본인 id와 매칭 수정
            if (slotData.index == 0) {
                when {
                    // 촬영된 이미지가 있으면 이미지 표시
                    slotData.uri != null -> {
                        CapturedImageDisplay(
                            imageUri = slotData.uri,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    // 카메라 권한이 있을 때
                    cameraPermissionState.status.isGranted -> {
                        CameraPreview(
                            modifier = Modifier.fillMaxSize(),
                            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA,
                            onImageCaptureReady = { capture ->
                                imageCapture = capture // ImageCapture 준비 완료
                            })
                    }
                    // 권한 설명이 필요할 때
                    cameraPermissionState.status.shouldShowRationale -> {
                        CameraPermissionRationale(
                            onRequestPermission = { cameraPermissionState.launchPermissionRequest() })
                    }
                    // 처음 권한 요청 또는 거부됨
                    else -> {
                        CameraPermissionRequest(
                            onRequestPermission = { cameraPermissionState.launchPermissionRequest() })
                    }
                }

                // 촬영 상태 오버레이
                if (isCapturing) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = White)
                    }
                }

                // 버튼들 - 상태에 따라 다른 버튼 표시
                when {
                    // 촬영된 이미지가 있을 때 - 다시 촬영 버튼
                    slotData.uri != null -> {
                        IconButton(
                            modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                                // todo 재촬영 기능 넣을지 말지
//                                slotData.uri = null
                            }) {
                            RetakeButton()
                        }
                    }
                    // 카메라 프리뷰 상태일 때 - 촬영 버튼
                    cameraPermissionState.status.isGranted && !isCapturing -> {
                        IconButton(
                            modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                                imageCapture?.let { capture ->
                                    coroutineScope.launch {
                                        isCapturing = true
                                        try {
                                            // todo 콜백으로 변경 시간초과 될 경우 자동 촬영 기능
                                            val uri = capturePhoto(capture, context)
                                            if (uri != null) {
                                                onCapture(slotData.index, uri)
                                            }
                                        } finally {
                                            isCapturing = false
                                        }
                                    }
                                }
                            }) {
                            CaptureButton()
                        }
                    }
                }
            }


        }
        if (slotData.isSelected) {
            Box(
                modifier = modifier
                    .align(if (slotData.index % 2 == 0) Alignment.TopStart else Alignment.TopEnd)
                    .offset(
                        x = if (slotData.index % 2 == 0) -15.dp else 15.dp, y = -15.dp
                    )
            ) {
                SelectedUser(
                    imageUrl = "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
                )
            }
        }
    }
}


@Composable
fun CapturedImageDisplay(
    imageUri: Uri, modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = imageUri,
            contentDescription = "Captured Photo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Box 크기에 맞게 크롭
        )
    }
}

// ✅ 촬영 버튼 컴포넌트
@Composable
fun CaptureButton() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(color = PrimaryDefault, shape = CircleShape)
    ) {
        Image(
            modifier = Modifier
                .size(13.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.camera),
            contentDescription = "capture",
            colorFilter = ColorFilter.tint(color = White)
        )
    }
}

// ✅ 다시 촬영 버튼 컴포넌트
@Composable
fun RetakeButton() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(color = SecondaryDefault, shape = CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.camera),
            contentDescription = "retake",
            tint = White
        )
    }
}


// 권한 요청 UI들
@Composable
fun CameraPermissionRequest(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.camera),
            contentDescription = "Camera",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "카메라 권한 필요", style = DetailRegular, color = Border, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "권한 허용",
            style = DetailRegular,
            color = PrimaryDefault,
            modifier = Modifier.clickableNoRipple { onRequestPermission() })
    }
}

@Composable
fun CameraPermissionRationale(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.camera),
            contentDescription = "Camera",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "사진 촬영을 위해\n카메라 권한이 필요합니다",
            style = DetailRegular.copy(fontSize = 10.sp),
            color = Border,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "설정에서 허용",
            style = DetailRegular.copy(fontSize = 10.sp),
            color = PrimaryDefault,
            modifier = Modifier.clickableNoRipple { onRequestPermission() })
    }
}

@Preview(showBackground = true)
@Composable
fun CameraSlotPreview() {
    CameraSlot(slotData = SlotType.Camera(0, 0, false, null), onCapture = { _, _ -> })
}
