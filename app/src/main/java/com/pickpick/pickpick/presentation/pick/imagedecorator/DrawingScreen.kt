package com.pickpick.pickpick.presentation.pick.imagedecorator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.presentation.pick.imagedecorator.ui.DrawingCanvas
import com.pickpick.pickpick.presentation.pick.imagedecorator.ui.DrawingToolbar
import com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel.DrawingViewModel

@Composable
fun DrawingScreen(
    modifier: Modifier = Modifier, viewModel: DrawingViewModel = hiltViewModel()
) {
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

        // 메인 캔버스
        DrawingCanvas(
            uiState = uiState,
            onInitializeBitmap = viewModel::initializeBitmap,
            onStartNewPath = viewModel::startNewPath,
            onAddPointToPath = viewModel::addPointToPath,
            onFinishPath = viewModel::finishPath,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White)
        )

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
