package com.pickpick.pickpick.presentation.pick.selectframe.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FrameViewModel @Inject constructor() :
    BaseViewModel<FrameUiState>(FrameUiState()) {

    fun selectFrame(index: Int) {
        _uiState.update {
            it.copy(
                selectedFrameIndex = index
            )
        }
    }

}