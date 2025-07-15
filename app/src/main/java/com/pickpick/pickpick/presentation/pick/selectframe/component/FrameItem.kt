package com.pickpick.pickpick.presentation.pick.selectframe.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailBold

@Composable
fun FrameItem(
    modifier: Modifier = Modifier,
    index: Int,
    frameRes: Int,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (selectedIndex == index) {
            Text(
                text = "my pick",
                style = DetailBold.copy(PrimaryDefault),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .clickable { onSelect(index) }
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = if (selectedIndex == index) 3.dp else 0.dp,
                        color = if (selectedIndex == index) PrimaryDefault else Color.Transparent
                    )
                    .padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = frameRes),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 120.dp)
                )
            }

            if (selectedIndex == index) {
                Image(
                    painter = painterResource(R.drawable.icon_check),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 16.dp, y = (-16).dp)
                )
            }
        }
    }
}