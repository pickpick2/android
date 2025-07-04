package com.pickpick.pickpick.presentation.room.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class RoomUiState(
    val roomName: String = "",
    val people: Int = 0,
    val enabled: Boolean = false,
) : UiState
