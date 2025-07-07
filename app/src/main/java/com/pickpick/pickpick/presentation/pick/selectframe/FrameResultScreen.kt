package com.pickpick.pickpick.presentation.pick.selectframe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.ParticipationAppBar
import com.pickpick.pickpick.core.ui.component.timer.TimerText
import com.pickpick.pickpick.core.ui.component.timer.rememberTimerState
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.pick.selectframe.viewmodel.FrameViewModel

@Composable
fun FrameResultScreen(
    modifier: Modifier = Modifier,
    viewModel: FrameViewModel = hiltViewModel(),
    onNavigateToNext: () -> Unit,
) {

    val profileImages = MutableList(3) {
        "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
    }

    val timerState = rememberTimerState(
        initialSeconds = 3, autoStart = true
    )

    LaunchedEffect(timerState.isCompleted) {
        if (timerState.isCompleted) {
            onNavigateToNext()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
    ) {
        ParticipationAppBar(
            profileImages = profileImages
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
        ) {

            Text(
                text = stringResource(R.string.frame_title),
                style = Heading1
            )

            Spacer(modifier = Modifier.height(8.dp))

            TimerText(time = timerState.remainingSeconds)

            Spacer(modifier = Modifier.height(40.dp))

        }
    }

}

@Composable
@Preview(showBackground = true)
fun FramePreview() {
    FrameResultScreen(onNavigateToNext = {})
}