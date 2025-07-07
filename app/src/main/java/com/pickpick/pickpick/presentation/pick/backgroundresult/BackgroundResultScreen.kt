package com.pickpick.pickpick.presentation.pick.backgroundresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.ParticipationAppBar
import com.pickpick.pickpick.core.ui.component.timer.TimerText
import com.pickpick.pickpick.core.ui.component.timer.rememberTimerState
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Bold
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1


@Composable
fun BackgroundResultScreen(
    modifier: Modifier = Modifier,
    onNavigateToSlot: () -> Unit,
) {

    val profileImages = MutableList(3) {
        "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
    }

    var timerState = rememberTimerState(initialSeconds = 3)

    LaunchedEffect(timerState.isCompleted) {
        if (timerState.isCompleted) {
            onNavigateToSlot()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ParticipationAppBar(
            profileImages = profileImages
        )
        Column(
            modifier = modifier.padding(
                horizontal = 36.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(60.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    10.dp, alignment = Alignment.CenterVertically
                )
            ) {
                Text("new! 최고심 성공한다", style = Heading1)
                Text(stringResource(R.string.background_selected), style = Body1Bold)
                TimerText(time = timerState.remainingSeconds)
            }
            Spacer(modifier = modifier.height(40.dp))

            Image(
                painter = painterResource(R.drawable.frame), contentDescription = "background"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CompleteBackgroundScreenPreview() {
    BackgroundResultScreen(onNavigateToSlot = {})
}
