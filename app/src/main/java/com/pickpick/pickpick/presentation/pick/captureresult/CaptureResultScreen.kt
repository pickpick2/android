package com.pickpick.pickpick.presentation.pick.captureresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.ParticipationAppBar
import com.pickpick.pickpick.core.ui.component.pickpick.SlotDescription
import com.pickpick.pickpick.core.ui.component.timer.rememberTimerState
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1

@Composable
fun CaptureResultScreen(
    modifier: Modifier = Modifier,
    onNavigateToDecorate: () -> Unit,
) {
    val profileImages = MutableList(3) {
        "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
    }


    var timerState = rememberTimerState(initialSeconds = 3)
    LaunchedEffect(timerState.isCompleted) {
        if (timerState.isCompleted) {
            onNavigateToDecorate()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ParticipationAppBar(
            profileImages = profileImages
        )
        SlotDescription(
            titleSub1String = stringResource(R.string.photo_capture_completed),
            titleSub2String = stringResource(R.string.navigate_to_photo_editor),
            descriptionString = stringResource(
                R.string.auto_progress_timer, timerState.remainingSeconds
            ),
            descriptionColor = PrimaryDefault
        )
        Text(
            text = stringResource(R.string.second_timer, timerState.remainingSeconds),
            style = Heading1
        )

        Spacer(modifier = modifier.height(10.dp))

//        SlotGridLayout(
//            items = slotItems, colCount = 2, onSlotAction = { action ->
//                if (action is SlotAction.SelectPosition) {
//                    viewModel.selectSlot(action.index)
//                }
//            })
    }
}
