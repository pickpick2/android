package com.pickpick.pickpick.presentation.album.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor() : BaseViewModel<AlbumUiState>(AlbumUiState()) {

}