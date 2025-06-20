package com.pickpick.pickpick.core.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
/**
 * inner BottomBorder
 */
fun Modifier.bottomBorder(
    strokeWidth: Dp = 1.dp,
    color : Color = Border
) = this.then(Modifier.drawBehind {
    val borderSize = strokeWidth.toPx()
    val offSetY = size.height - borderSize / 2
    drawLine(
        color = color,
        start = Offset(x = 0f, y = offSetY),
        end = Offset(x = size.width, y = offSetY),
        strokeWidth = borderSize,
    )
})


/**
 * 클릭 효과 없는 클릭 처리를 위한 확장 함수
 */
@Composable
fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier {
    return this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null, // 리플 효과 제거
        onClick = onClick
    )
}
