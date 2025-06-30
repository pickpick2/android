package com.pickpick.pickpick.presentation.findpassword.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor() :
    BaseViewModel<FindPasswordUiState>(FindPasswordUiState()) {
    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }
}
