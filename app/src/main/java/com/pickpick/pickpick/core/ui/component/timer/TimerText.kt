package com.pickpick.pickpick.core.ui.component.timer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Regular

@Composable
fun TimerText(
    modifier: Modifier = Modifier,
    time: Int,
    iconSize: Dp = 12.dp,
    textStyle: TextStyle = Body2Regular.copy(color = PrimaryDefault)
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = modifier.size(iconSize),
            painter = painterResource(R.drawable.timer),
            contentDescription = "timer"
        )
        Spacer(modifier = modifier.width(5.dp))
        Text(
            stringResource(R.string.auto_selection_timer, time), style = textStyle
        )
    }
}
