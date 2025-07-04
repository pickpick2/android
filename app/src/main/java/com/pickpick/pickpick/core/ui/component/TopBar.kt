package com.pickpick.pickpick.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Bold

@Composable
fun CommonBackTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBackClick: (() -> Unit),
) {
    Box(
        modifier = modifier
            .background(White)
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIconImage(modifier = Modifier, onBackClick)
        }

        title?.let {
            TopBarText(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                fontColor = Black
            )
        }
    }
}

@Composable
fun BackIconImage(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    color: Color = Color.Black,
) {
    Image(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onBackClick() }
            ),
        painter = painterResource(id = R.drawable.icon_arrow_back),
        contentDescription = "back",
        colorFilter = ColorFilter.tint(color),
    )
}

@Composable
fun TopBarText(
    modifier: Modifier = Modifier,
    text: String,
    fontColor: Color,
) {
    Text(
        modifier = modifier,
        text = text,
        color = fontColor,
        style = Body1Bold,
    )
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview() {
    Column {
        CommonBackTopBar(title = "text", onBackClick = {})
    }
}