package com.pickpick.pickpick.presentation.auth.findemail.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class FindEmailUiState(
    val email: String = "",
    val errorText: String? = null
) : UiState
