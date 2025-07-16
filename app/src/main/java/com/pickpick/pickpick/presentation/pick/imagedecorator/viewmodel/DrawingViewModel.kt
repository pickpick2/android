package com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import androidx.compose.ui.graphics.Path as ComposePath


@HiltViewModel
class DrawingViewModel @Inject constructor() : BaseViewModel<DrawingUiState>(DrawingUiState()) {

    // Bitmap 렌더링 시스템 (래스터 - 실제 화면 출력용)
    private var androidBitmap: Bitmap? = null
    private var bitmapCanvas: Canvas? = null

    // 실시간 렌더링을 위한 변수들
    private var lastRenderTime = 0L
    private val renderInterval = 16L // 약 60FPS (16ms마다 렌더링)

    // Paint 객체들
    private val drawPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
    }

    private val eraserPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) // 픽셀을 투명하게
    }

    /**
     * 그리기 모드 변경
     */
    fun updateDrawMode(mode: DrawMode) {
        _uiState.update { it.copy(drawMode = mode) }
    }

    /**
     * 펜 색상 변경
     */
    fun updatePenColor(color: Color) {
        _uiState.update { it.copy(penColor = color) }
    }

    /**
     * 선 굵기 변경
     */
    fun updateStrokeWidth(width: Float) {
        _uiState.update { it.copy(strokeWidth = width) }
    }

    /**
     * 캔버스 크기가 정해지면 Bitmap 초기화
     */
    fun initializeBitmap(width: Int, height: Int) {
        if (androidBitmap == null) {
            androidBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            androidBitmap?.eraseColor(android.graphics.Color.WHITE)
            bitmapCanvas = Canvas(androidBitmap!!)

            _uiState.update {
                it.copy(bitmap = androidBitmap?.asImageBitmap())
            }
        }
    }

    /**
     * 새로운 Path 시작 (펜/지우개 공통)
     */
    fun startNewPath(startPoint: Offset) {
        val newPath = ComposePath()
        newPath.moveTo(startPoint.x, startPoint.y)

        _uiState.update {
            it.copy(
                currentPath = newPath, isDrawing = true
            )
        }
    }

    /**
     * Path에 점 추가 (펜/지우개 공통) + 실시간 렌더링
     */
    fun addPointToPath(point: Offset) {
        if (_uiState.value.isDrawing) {
            val updatedPath = ComposePath().apply {
                addPath(_uiState.value.currentPath)
                lineTo(point.x, point.y)
            }

            _uiState.update { it.copy(currentPath = updatedPath) }

            // 일정 주기마다 현재 Path를 Bitmap에 렌더링 (실시간 효과)
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastRenderTime >= renderInterval) {
                renderCurrentPathToBitmap()
                lastRenderTime = currentTime
            }
        }
    }

    /**
     * Path 완성 및 Bitmap에 최종 렌더링 (펜/지우개 공통)
     */
    fun finishPath() {
        val currentState = _uiState.value
        if (currentState.isDrawing) {
            val updatedPaths = if (currentState.drawMode == DrawMode.PEN) {
                // 펜 모드일 때만 Path 데이터 저장
                val finishedPath = DrawPath(
                    path = ComposePath().apply { addPath(currentState.currentPath) },
                    color = currentState.penColor,
                    strokeWidth = currentState.strokeWidth
                )
                currentState.paths + finishedPath
            } else {
                // 지우개 모드일 때는 Path 저장하지 않음
                currentState.paths
            }

            // 최종 렌더링 (펜/지우개 모두 마지막에 한번 더 정확하게)
            renderCurrentPathToBitmap()

            _uiState.update {
                it.copy(
                    paths = updatedPaths, currentPath = ComposePath(), isDrawing = false
                )
            }
        }
    }

    /**
     * 현재 그리고 있는 Path를 실시간으로 Bitmap에 렌더링 (펜/지우개 구분)
     */
    private fun renderCurrentPathToBitmap() {
        bitmapCanvas?.let { canvas ->
            val currentState = _uiState.value
            val androidPath = currentState.currentPath.asAndroidPath()

            when (currentState.drawMode) {
                DrawMode.PEN -> {
                    // 펜 모드: 일반 그리기
                    drawPaint.color = currentState.penColor.toArgb()
                    drawPaint.strokeWidth = currentState.strokeWidth
                    canvas.drawPath(androidPath, drawPaint)
                }

                DrawMode.ERASER -> {
                    // 지우개 모드: 픽셀을 투명하게
                    eraserPaint.strokeWidth = currentState.strokeWidth
                    canvas.drawPath(androidPath, eraserPaint)
                }
            }

            _uiState.update {
                it.copy(bitmap = androidBitmap?.asImageBitmap())
            }
        }
    }

    /**
     * 개별 Path를 Bitmap에 렌더링
     */
    private fun renderPathToBitmap(drawPath: DrawPath) {
        bitmapCanvas?.let { canvas ->
            drawPaint.color = drawPath.color.toArgb()
            drawPaint.strokeWidth = drawPath.strokeWidth

            // Compose Path를 Android Path로 변환
            val androidPath = drawPath.path.asAndroidPath()
            canvas.drawPath(androidPath, drawPaint)

            _uiState.update {
                it.copy(bitmap = androidBitmap?.asImageBitmap())
            }
        }
    }

    /**
     * 모든 Path를 Bitmap에 다시 렌더링 (Undo 시 사용)
     */
    private fun rerenderAllPaths() {
        androidBitmap?.let { bitmap ->
            // Bitmap 초기화 (흰색 배경)
            bitmap.eraseColor(android.graphics.Color.WHITE)

            // 모든 Path를 순서대로 렌더링
            _uiState.value.paths.forEach { drawPath ->
                renderPathToBitmap(drawPath)
            }
        }
    }
}
