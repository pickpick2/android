package com.pickpick.pickpick.presentation.album.viewmodel

import com.pickpick.pickpick.core.presentation.UiState
import com.pickpick.pickpick.core.result.ResultState
import com.pickpick.pickpick.domain.album.model.Photo

data class AlbumUiState(
    val albumResult: ResultState<List<Photo>> = ResultState.Loading,
    val photoResult: ResultState<Photo> = ResultState.Loading
) : UiState
