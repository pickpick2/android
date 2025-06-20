package com.pickpick.pickpick.presentation.signup.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val checkPassword: String = "",
    val emailErrorText: String? = null,
    val passwordErrorText: String? = null,
    val checkPasswordErrorText: String? = null,
    val checkPolicy: Boolean = false,
    val enabled: Boolean = false,
) : UiState
