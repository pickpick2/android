package com.pickpick.pickpick.presentation.album.viewmodel

import androidx.paging.PagingData
import com.pickpick.pickpick.core.presentation.UiState
import com.pickpick.pickpick.core.result.ResultState
import com.pickpick.pickpick.domain.album.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class AlbumUiState(
    val albumResult: ResultState<List<Photo>> = ResultState.Loading,
    val photoResult: ResultState<Photo> = ResultState.Loading,
    val pagedPhotos: Flow<PagingData<Photo>> = emptyFlow()
) : UiState
