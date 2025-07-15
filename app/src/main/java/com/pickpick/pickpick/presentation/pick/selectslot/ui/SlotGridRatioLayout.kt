package com.pickpick.pickpick.presentation.pick.selectslot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.pickpick.pickpick.FrameLayout
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotAction
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotItem
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotType
import com.pickpick.pickpick.core.util.SlotLayoutUtil
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout

@Composable
fun SlotGridRatioLayout(
    modifier: Modifier = Modifier,
    painter: Painter,
    slotType: SlotType,
    items: List<SlotType>,
    setFrameLayout: (FrameLayout) -> Unit,
    setSlotLayouts: (List<SlotLayout>) -> Unit,
    ratioSlotLayouts: List<SlotLayout>,
    onSlotAction: (SlotAction) -> Unit,
) {

    var imageSize by remember { mutableStateOf(Size.Zero) }
    var imagePosition by remember { mutableStateOf(Offset.Zero) }

    val density = LocalDensity.current

    // imageSize나 imagePosition이 변경될 때만 frameLayout 계산
    LaunchedEffect(imageSize, imagePosition) {
        if (imageSize != Size.Zero) {
            val frameLayout = FrameLayout(
                x = imagePosition.x,
                y = imagePosition.y,
                width = imageSize.width,
                height = imageSize.height
            )
            setFrameLayout(frameLayout)

            // 슬롯 레이아웃 계산
            val slotLayouts = SlotLayoutUtil.getSlotLayoutInfo(
                density = density, frameLayout = frameLayout, slotLayouts = ratioSlotLayouts
            )
            setSlotLayouts(slotLayouts)
        }
    }

    Box(modifier = modifier.padding(horizontal = 24.dp)) {
        Image(
            painter = painter,
            contentDescription = "slot",
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    val intrinsicSize = painter.intrinsicSize
                    val layoutSize = coordinates.size.toSize()

                    if (intrinsicSize.isSpecified && layoutSize != Size.Zero) {
                        // 계산 로직
                        val scaleX = layoutSize.width / intrinsicSize.width
                        val scaleY = layoutSize.height / intrinsicSize.height
                        val scale = minOf(scaleX, scaleY)

                        val renderedWidth = intrinsicSize.width * scale
                        val renderedHeight = intrinsicSize.height * scale

                        val offsetX = (layoutSize.width - renderedWidth) / 2
                        val offsetY = (layoutSize.height - renderedHeight) / 2

                        val parentPosition = coordinates.positionInParent()

                        // 상태 업데이트를 조건부로
                        val newImagePosition = Offset(
                            x = parentPosition.x + offsetX, y = parentPosition.y + offsetY
                        )
                        val newImageSize = Size(renderedWidth, renderedHeight)

                        if (imagePosition != newImagePosition) {
                            imagePosition = newImagePosition
                        }
                        if (imageSize != newImageSize) {
                            imageSize = newImageSize
                        }
                    }
                })
        when (slotType) {
            is SlotType.Position -> {
                repeat(items.size) { index ->
                    SlotItem(
                        modifier = modifier,
                        slotType = items[index],
                        onSlotAction = onSlotAction,
                    )
                }
            }

            is SlotType.Camera -> {
                repeat(items.size) { index ->
                    SlotItem(
                        modifier = modifier,
                        slotType = items[index],
                        onSlotAction = onSlotAction,
                    )
                }
            }
        }

    }


}
