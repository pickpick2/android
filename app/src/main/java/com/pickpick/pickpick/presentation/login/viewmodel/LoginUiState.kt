package com.pickpick.pickpick.presentation.login.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorText: String? = null,
    val enabled: Boolean = false,
) : UiState
