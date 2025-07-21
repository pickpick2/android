package com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.pickpick.pickpick.core.presentation.UiState
import androidx.compose.ui.graphics.Path as ComposePath

/**
 * 그리기 모드
 */
enum class DrawMode {
    PEN, ERASER
}

/**
 * Path 기반 그리기 데이터 (벡터)
 */
data class DrawPath(
    val path: ComposePath, val color: Color = Color.Black, val strokeWidth: Float = 5f
)

/**
 * Drawing UI 상태
 */
data class DrawingUiState(
    val drawMode: DrawMode = DrawMode.PEN,
    val penColor: Color = Color.Black,
    val strokeWidth: Float = 5f,
    val paths: List<DrawPath> = emptyList(),
    val bitmap: ImageBitmap? = null,
    val isDrawing: Boolean = false,
    val currentPath: ComposePath = ComposePath()
) : UiState
