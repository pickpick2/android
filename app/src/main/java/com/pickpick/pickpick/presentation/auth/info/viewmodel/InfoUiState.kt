package com.pickpick.pickpick.presentation.auth.info.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class InfoUiState(
    val nickname: String = "",
    val nicknameErrorText: String? = null,
    val enabled: Boolean = false,
) : UiState