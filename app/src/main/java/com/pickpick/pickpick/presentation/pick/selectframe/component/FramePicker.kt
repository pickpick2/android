package com.pickpick.pickpick.presentation.pick.selectframe.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FramePicker(
    selectedIndex: Int?,
    onSelect: (Int) -> Unit
) {
    val frameList = listOf(
        R.drawable.frame_1,
        R.drawable.frame_2,
        R.drawable.frame_3,
        R.drawable.frame_4
    )

    val itemWidth = (LocalConfiguration.current.screenWidthDp.dp - 16.dp * 3) / 2

//    FlowRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        maxItemsInEachRow = 2,
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        frameList.forEachIndexed { index, frameRes ->
//            FrameItem(
//                modifier = Modifier.width(itemWidth),
//                index = index,
//                frameRes = frameRes,
//                selectedIndex = selectedIndex,
//                onSelect = onSelect
//            )
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 0,1번 FrameItem을 Column 안에 넣기
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                (0..1).forEach { i ->
                    FrameItem(
                        modifier = Modifier.fillMaxWidth(),
                        index = i,
                        frameRes = frameList[i],
                        selectedIndex = selectedIndex,
                        onSelect = { onSelect(i) }
                    )
                }
            }

            // 2번 FrameItem
            FrameItem(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                index = 2,
                frameRes = frameList[2],
                selectedIndex = selectedIndex,
                onSelect = { onSelect(2) }
            )
        }

        // 3번 FrameItem
        FrameItem(
            modifier = Modifier.fillMaxWidth(),
            index = 3,
            frameRes = frameList[3],
            selectedIndex = selectedIndex,
            onSelect = { onSelect(3) }
        )
    }



}
