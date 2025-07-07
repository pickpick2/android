package com.pickpick.pickpick.core.ui.component.pickpick

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.Warning
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1

@Composable
fun SlotDescription(
    modifier: Modifier = Modifier,
    titleSub1String: String = stringResource(R.string.within_time_limit),
    titleSub2String: String,
    descriptionString: String,
    descriptionColor: Color = Warning
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = modifier.height(35.dp))
        Text(
            titleSub1String, style = Heading1.copy(color = PrimaryDefault)
        )
        Text(titleSub2String, style = Heading1)
        Spacer(modifier = modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.timer),
                contentDescription = "timer",
                colorFilter = ColorFilter.tint(color = descriptionColor)
            )
            Spacer(modifier = modifier.width(5.dp))
            Text(
                descriptionString, style = DetailRegular.copy(color = descriptionColor)
            )
        }
        Spacer(modifier = modifier.height(30.dp))

    }
}
