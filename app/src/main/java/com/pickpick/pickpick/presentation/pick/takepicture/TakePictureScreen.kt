package com.pickpick.pickpick.presentation.pick.takepicture

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.ParticipationAppBar
import com.pickpick.pickpick.core.ui.component.pickpick.SlotDescription
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotAction
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotType
import com.pickpick.pickpick.core.ui.component.timer.rememberTimerState
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.pick.selectslot.ui.SlotGridRatioLayout
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout
import com.pickpick.pickpick.presentation.pick.takepicture.viewmodel.CameraViewModel

@Composable
fun TakePictureScreen(
    modifier: Modifier = Modifier,
    viewModel: CameraViewModel = hiltViewModel(),
    onNavigateToResult: () -> Unit,
) {


    val profileImages = MutableList(3) {
        "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // todo dummy data 나중에 삭제 예정
    val painter = painterResource(id = R.drawable.frame)

    val slotItems = uiState.slotLayouts.map {
        SlotType.Camera(
            slotLayout = it, null
        )
    }

    var timerState = rememberTimerState(initialSeconds = 20)
    LaunchedEffect(timerState.isCompleted) {
        if (timerState.isCompleted) {
//            onNavigateToResult()
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
            titleSub2String = stringResource(R.string.photo_capture_instruction),
            descriptionString = stringResource(R.string.timeout_auto_capture_warning)
        )

        Text(
            text = stringResource(R.string.second_timer, timerState.remainingSeconds),
            style = Heading1
        )
        Spacer(modifier = modifier.height(10.dp))

        SlotGridRatioLayout(
            slotType = SlotType.Camera(slotLayout = SlotLayout(), null),
            items = slotItems,
            painter = painter,
            setFrameLayout = viewModel::setFrameLayout,
            setSlotLayouts = viewModel::setSlotLayouts,
            ratioSlotLayouts = uiState.ratioSlotLayouts,
            onSlotAction = { action ->
                if (action is SlotAction.CapturePhoto) {
                    viewModel.capturePhoto(uri = action.uri)
                }
            })


    }
}
