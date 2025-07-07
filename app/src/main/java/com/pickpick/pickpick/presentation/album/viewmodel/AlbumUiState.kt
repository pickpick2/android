package com.pickpick.pickpick.presentation.album.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class AlbumUiState(
    val albumList: List<String> = emptyList()
) : UiState
