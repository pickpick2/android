package com.pickpick.pickpick.presentation.pick.room.viewmodel

import androidx.lifecycle.viewModelScope
import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.result.toResultState
import com.pickpick.pickpick.domain.pick.usecase.CreateRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val createRoomUseCase: CreateRoomUseCase,
) : BaseViewModel<RoomUiState>(RoomUiState()) {

    fun updateState(block: (RoomUiState) -> RoomUiState) {
        _uiState.update(block)
    }

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

    // 방 생성
    fun createRoom(roomCapacity: Int) {
        viewModelScope.launch {
            val result = createRoomUseCase(roomCapacity)
            updateState { it.copy(roomResult = result.toResultState()) }
        }
    }

}