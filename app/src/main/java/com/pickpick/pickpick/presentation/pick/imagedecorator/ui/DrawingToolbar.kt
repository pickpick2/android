package com.pickpick.pickpick.presentation.pick.imagedecorator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel.DrawMode
import com.pickpick.pickpick.presentation.pick.imagedecorator.viewmodel.DrawingUiState

@Composable
fun DrawingToolbar(
    uiState: DrawingUiState,
    onDrawModeChange: (DrawMode) -> Unit,
    onStrokeWidthChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 모드 선택 버튼들
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { onDrawModeChange(DrawMode.PEN) }, modifier = Modifier.background(
                    color = if (uiState.drawMode == DrawMode.PEN) MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    )
                    else Color.Transparent, shape = CircleShape
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "펜 모드",
                    tint = if (uiState.drawMode == DrawMode.PEN) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { onDrawModeChange(DrawMode.ERASER) }, modifier = Modifier.background(
                    color = if (uiState.drawMode == DrawMode.ERASER) MaterialTheme.colorScheme.error.copy(
                        alpha = 0.2f
                    )
                    else Color.Transparent, shape = CircleShape
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "지우개 모드",
                    tint = if (uiState.drawMode == DrawMode.ERASER) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // 굵기 조절
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("굵기")
            Slider(
                value = uiState.strokeWidth,
                onValueChange = onStrokeWidthChange,
                valueRange = if (uiState.drawMode == DrawMode.ERASER) 10f..50f else 1f..20f,
                modifier = Modifier.width(120.dp)
            )
            Text("${uiState.strokeWidth.toInt()}")
        }
    }
}
