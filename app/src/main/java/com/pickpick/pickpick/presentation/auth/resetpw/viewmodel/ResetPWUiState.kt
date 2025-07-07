package com.pickpick.pickpick.presentation.auth.resetpw.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class ResetPWUiState(
    val password: String = "",
    val checkPassword: String = "",
    val passwordErrorText: String? = null,
    val checkPasswordErrorText: String? = null,
    val enabled: Boolean = false,
) : UiState
