package com.pickpick.pickpick.presentation.findemail.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindEmailViewModel @Inject constructor() :
    BaseViewModel<FindEmailUiState>(FindEmailUiState()) {
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }
}
