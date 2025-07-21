package com.pickpick.pickpick.presentation.pick.room.viewmodel

import com.pickpick.pickpick.core.presentation.UiState
import com.pickpick.pickpick.core.result.ResultState
import com.pickpick.pickpick.domain.pick.model.Room

data class RoomUiState(
    val roomName: String = "",
    val people: Int = 0,
    val enabled: Boolean = false,
    val roomResult: ResultState<Room> = ResultState.Loading,
) : UiState
