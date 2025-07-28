package com.pickpick.pickpick.presentation.pick.room

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.result.ResultState
import com.pickpick.pickpick.core.ui.component.BackLayout
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.album.component.CustomDropdown
import com.pickpick.pickpick.presentation.pick.room.viewmodel.RoomViewModel
import com.pickpick.pickpick.presentation.websocket.viewmodel.WebSocketViewModel

@Composable
fun CreateRoomScreen(
    modifier: Modifier = Modifier,
    roomViewModel: RoomViewModel = hiltViewModel(),
    webSocketViewModel: WebSocketViewModel = hiltViewModel(),
    onNavigateToComplete: () -> Unit,
    onBackClick: () -> Unit
) {

    val uiState by roomViewModel.uiState.collectAsStateWithLifecycle()
    val messageState by webSocketViewModel.messageState.collectAsStateWithLifecycle()

    when (uiState.roomResult) {
        is ResultState.Success -> {
            val roomInfo = (uiState.roomResult as ResultState.Success).data
            webSocketViewModel.connect(roomId = roomInfo.roomId)

            Log.d("WebSocket", messageState.toString())

//            onNavigateToComplete()
        }

        else -> {}
    }

    // TODO: 방 생성 성공 시 웹소켓 연결

    BackLayout(
        title = stringResource(R.string.create_room_top_bar),
        onBackClick = onBackClick
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 56.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.create_room_title),
                style = Heading1
            )

            Spacer(modifier = Modifier.height(70.dp))

            UnderlineTextField(
                value = uiState.roomName,
                onValueChange = roomViewModel::updateRoomName,
                placeholder = {
                    Text(
                        text = stringResource(R.string.create_room_name_placeholder),
                        style = DetailRegular.copy(color = Border)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // TODO: 인원 선택 추가
            var selectedPeople by remember { mutableStateOf("인원 선택") }
            val options = listOf("1", "2", "3", "4", "5", "6")

            CustomDropdown(
                selectedOption = selectedPeople,
                options = options,
                onOptionSelected = {
                    selectedPeople = it
                    roomViewModel.updatePeople(it.toInt())
                },
                icon = R.drawable.icon_people
            )

            Spacer(modifier = Modifier.height(70.dp))

            MainButton(
                text = stringResource(R.string.create_room),
                enabled = uiState.enabled,
                onClick = roomViewModel::createRoom
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun CreateRoomScreenPreview() {
    CreateRoomScreen(onNavigateToComplete = {}, onBackClick = {})
}