package com.pickpick.pickpick.presentation.auth.start.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

sealed class GuestLoginResult {
    object Loading : GuestLoginResult()
    data class Success(val accessToken: String? = null) : GuestLoginResult()
    data class Error(val message: Int) : GuestLoginResult()
}

data class GuestUiState(
    val guestLoginResult: GuestLoginResult = GuestLoginResult.Success()
) : UiState
