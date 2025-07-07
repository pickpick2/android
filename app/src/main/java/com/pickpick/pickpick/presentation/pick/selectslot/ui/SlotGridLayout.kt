package com.pickpick.pickpick.presentation.pick.selectslot.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotAction
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotItem
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotType

@Composable
fun SlotGridLayout(
    modifier: Modifier = Modifier, colCount: Int, items: List<SlotType>,
    onSlotAction: (SlotAction) -> Unit,
) {

    val slotType = items.first()
    when (slotType) {
        is SlotType.Position -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(colCount),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(26.dp),
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(items) { index, item ->
                    SlotItem(
                        modifier = modifier,
                        slotType = item,
                        onSlotAction = onSlotAction,
                    )
                }
            }
        }

        is SlotType.Camera -> {
            // 행 단위로 분할
            val rows = items.chunked(colCount)
            // Column으로 한 이유
            // Lazy로 하면 Camera가 안보일 경우 해당 컴포저블의 리소스를 해제하기 때문에
            // 카메라도 같이 해제되어 다시 렌더링 할 때 불필요한 재시작 및 딜레이가 발생하여 Column으로 선택하였습니다!
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(26.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rows.forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowItems.forEach { item ->
                            SlotItem(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                slotType = item,
                                onSlotAction = onSlotAction
                            )
                        }

                        // 마지막 행이 colCount보다 적을 때 빈 공간 채우기
                        repeat(colCount - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }


}
