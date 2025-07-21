package com.pickpick.pickpick.presentation.pick.imagedecorator.ui

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel.DrawingUiState

@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    uiState: DrawingUiState,
    onInitializeBitmap: (Int, Int) -> Unit,
    onStartNewPath: (Offset) -> Unit,
    onAddPointToPath: (Offset) -> Unit,
    onFinishPath: () -> Unit,
) {
    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                // 캔버스 크기 확정 시 Bitmap 초기화
                onInitializeBitmap(size.width, size.height)

                detectDragGestures(onDragStart = { offset ->
                    onStartNewPath(offset)
                }, onDragEnd = {
                    onFinishPath()
                }, onDrag = { change, offset ->
                    Log.d("Drawing Pen", "x = ${change.position.x} y = ${change.position.y}")

                    onAddPointToPath(change.position)
                })
            }) {
        // 완성된 Bitmap 그리기 (지우개 효과 + 실시간 그리기 모두 포함)
        uiState.bitmap?.let { bitmap ->
            drawImage(bitmap)
        }

    }


}
