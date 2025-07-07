package com.pickpick.pickpick.presentation.pick.selectframe.component

import android.R.attr.maxWidth
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailBold

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

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        frameList.forEachIndexed { index, frameRes ->
            FrameItem(
                index = index,
                frameRes = frameRes,
                selectedIndex = selectedIndex,
                onSelect = onSelect
            )
        }
    }

}
