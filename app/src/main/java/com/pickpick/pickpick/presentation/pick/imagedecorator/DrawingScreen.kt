package com.pickpick.pickpick.presentation.pick.imagedecorator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.domain.pick.model.LayoutInfo
import com.pickpick.pickpick.presentation.pick.imagedecorator.ui.DrawingCanvas
import com.pickpick.pickpick.presentation.pick.imagedecorator.ui.DrawingToolbar
import com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel.DrawingViewModel
import kotlin.math.min

@Composable
fun DrawingScreen(
    modifier: Modifier = Modifier, viewModel: DrawingViewModel = hiltViewModel()
) {
    // 서버에서 받은 논리 캔버스 크기
    // 서버 캔버스가 클라이언트 캔버스보다 작아야 크기 및 배치 정상 작동
    val logicalWidth = 800f
    val logicalHeight = 1700f

    val colorList = listOf<Color>(
        Color.Black, Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Cyan, Color.Magenta,
        Color.Gray, Color.LightGray, Color.White,
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        // 상단 툴바
        DrawingToolbar(
            uiState = uiState,
            onDrawModeChange = viewModel::updateDrawMode,
            onStrokeWidthChange = viewModel::updateStrokeWidth,
            modifier = Modifier.padding(16.dp)
        )
        BoxWithConstraints(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            val density = LocalDensity.current

            // 클라이언트 기준 꾸미기 영역 최대 크기
            val canvasWidthPx = with(density) { this@BoxWithConstraints.maxWidth.toPx() }
            val canvasHeightPx = with(density) { this@BoxWithConstraints.maxHeight.toPx() }

            // 캔버스 비율을 유지하기 위해 가장 스케일의 가장 작은걸 선택
            val scale = min(canvasWidthPx / logicalWidth, canvasHeightPx / logicalHeight)

            // 남는 영역 중앙 배치를 위한 크기
            val remainWidth = canvasWidthPx - (logicalWidth * scale)
            val remainHeight = canvasHeightPx - (logicalHeight * scale)


            val layoutInfo = remember(maxWidth, maxHeight) {
                LayoutInfo(
                    logicalWidth = logicalWidth,
                    logicalHeight = logicalHeight,
                    translationX = logicalWidth * ((scale - 1) / 2) + remainWidth / 2,
                    translationY = logicalHeight * ((scale - 1) / 2) + remainHeight / 2,
                    scaleX = scale,
                    scaleY = scale
                )
            }

            val canvasWidth = with(density) {
                layoutInfo.logicalWidth.toDp()
            }
            val canvasHeight = with(density) {
                layoutInfo.logicalHeight.toDp()
            }

            // 꾸미기 영역
            Box(
                modifier = modifier
                    .size(canvasWidth, canvasHeight)
                    .graphicsLayer {
                        translationX = layoutInfo.translationX
                        translationY = layoutInfo.translationY
                        scaleX = layoutInfo.scaleX
                        scaleY = layoutInfo.scaleY
                    }) {
                // 메인 캔버스
                DrawingCanvas(
                    modifier = Modifier.matchParentSize(),
                    uiState = uiState,
                    onInitializeBitmap = viewModel::initializeBitmap,
                    onStartNewPath = viewModel::startNewPath,
                    onAddPointToPath = viewModel::addPointToPath,
                    onFinishPath = viewModel::finishPath,
                )
            }

        }


        AnimatedVisibility(
            visible = true,
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .horizontalScroll(rememberScrollState())
            ) {
                repeat(colorList.size) { index ->
                    IconButton(
                        onClick = {
                            viewModel.updatePenColor(colorList[index])
                        }, modifier = modifier.background(
                            shape = CircleShape, color = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = modifier
                                .size(32.dp)
                                .background(color = colorList[index], shape = CircleShape)
                                .border(width = 2.dp, color = Color.White, shape = CircleShape)
                        )
                    }
                }

            }
        }
    }
}
