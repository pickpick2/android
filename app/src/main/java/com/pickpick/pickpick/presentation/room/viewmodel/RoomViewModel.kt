package com.pickpick.pickpick.presentation.room.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor() : BaseViewModel<RoomUiState>(RoomUiState()) {

    fun updateRoomName(roomName: String) {
        val enabled = enabledButton(roomName, _uiState.value.people)

        _uiState.update {
            it.copy(roomName = roomName, enabled = enabled)
        }
    }

    fun updatePeople(people: Int) {
        val enabled = enabledButton(_uiState.value.roomName, people)

        _uiState.update {
            it.copy(people = people, enabled = enabled)
        }
    }

    private fun enabledButton(
        roomName: String, people: Int
    ): Boolean =
        roomName.isNotEmpty() && people > 0

}