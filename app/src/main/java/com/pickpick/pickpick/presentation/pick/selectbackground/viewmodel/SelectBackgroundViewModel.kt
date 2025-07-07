package com.pickpick.pickpick.presentation.pick.selectbackground.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectBackgroundViewModel @Inject constructor() :
    BaseViewModel<SelectBackgroundUiState>(SelectBackgroundUiState()) {

    fun selectBackground(index: Int) {
        _uiState.update {
            it.copy(
                selectedBackgroundIndex = index
            )
        }
    }

}
