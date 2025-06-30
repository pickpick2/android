package com.pickpick.pickpick.presentation.findpassword.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class FindPasswordUiState(
    val email: String = "",
    val errorText: String? = null
) : UiState
