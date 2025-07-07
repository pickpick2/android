package com.pickpick.pickpick.presentation.splash

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PickPickTheme
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault



@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryDefault),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo), contentDescription = "splash"
        )
    }

}


data class FrameLayoutInfo(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float
)

enum class ImageSizeMode {
    FILL_MAX_SIZE,
    FIXED_SIZE,
    FILL_WIDTH_FIXED_HEIGHT,
    CUSTOM_ASPECT_RATIO
}

// 프레임 예시
@Composable
fun FrameTestScreen(modifier: Modifier = Modifier) {
    val frameInfo = FrameLayoutInfo(
        x = 60 / 1200f,
        y = 420 / 1800f,
        width = 540 / 1200f,
        height = 650 / 1800f
    )

    var currentMode by remember { mutableStateOf(ImageSizeMode.FILL_MAX_SIZE) }
    var imagePosition by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(Size.Zero) }
    val density = LocalDensity.current
    val painter = painterResource(R.drawable.frame)

    // 로그로 각 모드별 결과 확인
    LaunchedEffect(imagePosition, imageSize, currentMode) {
        if (imageSize != Size.Zero) {
            Log.d("FlexibleTest", "=== Mode: $currentMode ===")
            Log.d("FlexibleTest", "Image Position: $imagePosition")
            Log.d("FlexibleTest", "Image Size: $imageSize")
            Log.d("FlexibleTest", "Image Ratio: ${imageSize.width / imageSize.height}")
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // 모드 선택 버튼들
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = { currentMode = ImageSizeMode.FILL_MAX_SIZE },
                modifier = Modifier.weight(1f)
            ) {
                Text("FillMax", style = MaterialTheme.typography.bodySmall)
            }
            Button(
                onClick = { currentMode = ImageSizeMode.FIXED_SIZE },
                modifier = Modifier.weight(1f)
            ) {
                Text("Fixed", style = MaterialTheme.typography.bodySmall)
            }
            Button(
                onClick = { currentMode = ImageSizeMode.FILL_WIDTH_FIXED_HEIGHT },
                modifier = Modifier.weight(1f)
            ) {
                Text("FillW", style = MaterialTheme.typography.bodySmall)
            }
            Button(
                onClick = { currentMode = ImageSizeMode.CUSTOM_ASPECT_RATIO },
                modifier = Modifier.weight(1f)
            ) {
                Text("Aspect", style = MaterialTheme.typography.bodySmall)
            }
        }

        // 현재 상태 정보
        Text(
            "Mode: $currentMode",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        if (imageSize != Size.Zero) {
            Text(
                "Image: ${imageSize.width.toInt()} × ${imageSize.height.toInt()}",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // ⭐ 핵심: 다양한 크기 모드로 이미지 표시
            val imageModifier = when (currentMode) {
                ImageSizeMode.FILL_MAX_SIZE -> Modifier.fillMaxSize()
                ImageSizeMode.FIXED_SIZE -> Modifier.size(300.dp, 400.dp)
                ImageSizeMode.FILL_WIDTH_FIXED_HEIGHT -> Modifier
                    .fillMaxWidth()
                    .height(500.dp)

                ImageSizeMode.CUSTOM_ASPECT_RATIO -> Modifier
                    .width(250.dp)
                    .height(350.dp)
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = when (currentMode) {
                    ImageSizeMode.FILL_MAX_SIZE -> Alignment.Center
                    ImageSizeMode.FIXED_SIZE -> Alignment.TopStart
                    ImageSizeMode.FILL_WIDTH_FIXED_HEIGHT -> Alignment.TopCenter
                    ImageSizeMode.CUSTOM_ASPECT_RATIO -> Alignment.BottomEnd
                }
            ) {
                Image(
                    painter = painter,
                    contentDescription = "frame",
                    modifier = imageModifier
                        .background(Color.Blue.copy(alpha = 0.1f)) // 이미지 컨테이너 영역 표시
                        .onGloballyPositioned { coordinates ->
                            // ⭐ 동일한 로직으로 실제 이미지 위치/크기 계산
                            val intrinsicSize = painter.intrinsicSize
                            val layoutSize = coordinates.size.toSize()

                            if (intrinsicSize.isSpecified && layoutSize != Size.Zero) {
                                // ContentScale.Fit 계산
                                val scaleX = layoutSize.width / intrinsicSize.width
                                val scaleY = layoutSize.height / intrinsicSize.height
                                val scale = minOf(scaleX, scaleY)

                                val renderedWidth = intrinsicSize.width * scale
                                val renderedHeight = intrinsicSize.height * scale

                                val offsetX = (layoutSize.width - renderedWidth) / 2
                                val offsetY = (layoutSize.height - renderedHeight) / 2

                                val parentPosition = coordinates.positionInParent()

                                imagePosition = Offset(
                                    x = parentPosition.x + offsetX,
                                    y = parentPosition.y + offsetY
                                )
                                imageSize = Size(renderedWidth, renderedHeight)
                            }
                        },
                    contentScale = ContentScale.Fit
                )
            }

            // ⭐ 이미지 크기와 위치에 관계없이 정확한 프레임 배치
            if (imageSize != Size.Zero) {
                with(density) {
                    val imageWidthDp = imageSize.width.toDp()
                    val imageHeightDp = imageSize.height.toDp()
                    val imageXDp = imagePosition.x.toDp()
                    val imageYDp = imagePosition.y.toDp()

                    val frameX = imageXDp + (imageWidthDp * frameInfo.x)
                    val frameY = imageYDp + (imageHeightDp * frameInfo.y)
                    val frameWidth = imageWidthDp * frameInfo.width
                    val frameHeight = imageHeightDp * frameInfo.height

                    // 프레임 Box
                    Box(
                        modifier = Modifier
                            .size(frameWidth, frameHeight)
                            .offset(frameX, frameY)
                            .background(
                                color = Color.Red.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(2.dp, Color.Red, RoundedCornerShape(8.dp))
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Frame",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // 디버깅: 실제 이미지 영역 표시
            if (imageSize != Size.Zero) {
                with(density) {
                    Box(
                        modifier = Modifier
                            .size(imageSize.width.toDp(), imageSize.height.toDp())
                            .offset(imagePosition.x.toDp(), imagePosition.y.toDp())
                            .border(2.dp, Color.Green.copy(alpha = 0.8f))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
fun FrameTestScreenPreview() {
    PickPickTheme {
        SplashScreen()
    }
}
