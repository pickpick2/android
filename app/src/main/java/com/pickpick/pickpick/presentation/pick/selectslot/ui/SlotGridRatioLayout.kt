package com.pickpick.pickpick.presentation.pick.selectslot.ui

import android.util.Log
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
            Log.d("SelectSlotScreen", "setSlotLayout")
            val frameLayout = FrameLayout(
                x = imagePosition.x,
                y = imagePosition.y,
                width = imageSize.width,
                height = imageSize.height
            )
            setFrameLayout(frameLayout)

            // 🔥 슬롯 레이아웃 계산도 여기서
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
                // 행 단위로 분할
//            val rows = items.chunked(colCount)
//            // Column으로 한 이유
//            // Lazy로 하면 Camera가 안보일 경우 해당 컴포저블의 리소스를 해제하기 때문에
//            // 카메라도 같이 해제되어 다시 렌더링 할 때 불필요한 재시작 및 딜레이가 발생하여 Column으로 선택하였습니다!
//            Column(
//                modifier = modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//                    .padding(26.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                rows.forEach { rowItems ->
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(10.dp)
//                    ) {
//                        rowItems.forEach { item ->
//                            SlotItem(
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .aspectRatio(1f),
//                                slotType = item,
//                                onSlotAction = onSlotAction
//                            )
//                        }
//
//                        // 마지막 행이 colCount보다 적을 때 빈 공간 채우기
//                        repeat(colCount - rowItems.size) {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }
//            }
            }
        }

    }


}
