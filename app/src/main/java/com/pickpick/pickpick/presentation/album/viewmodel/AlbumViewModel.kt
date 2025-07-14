package com.pickpick.pickpick.presentation.album.viewmodel

import androidx.lifecycle.viewModelScope
import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.result.toResultState
import com.pickpick.pickpick.domain.album.usecase.DeletePhotoUseCase
import com.pickpick.pickpick.domain.album.usecase.GetAlbumsUseCase
import com.pickpick.pickpick.domain.album.usecase.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getPhotoUseCase: GetPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : BaseViewModel<AlbumUiState>(AlbumUiState()) {

    fun updateState(block: (AlbumUiState) -> AlbumUiState) {
        _uiState.update(block)
    }

    fun fetchAlbums(
        query: String?,
        cursor: String?,
        size: Int
    ) {
        viewModelScope.launch {
            val result = getAlbumsUseCase(query, cursor, size)
            updateState { it.copy(albumResult = result.toResultState()) }
        }
    }

    fun fetchPhoto(albumId: String) {
        viewModelScope.launch {
            val result = getPhotoUseCase(albumId)
            updateState { it.copy(photoResult = result.toResultState()) }
        }
    }

    fun removePhoto(albumId: String) {
        viewModelScope.launch {
            deletePhotoUseCase(albumId)
        }
    }

}