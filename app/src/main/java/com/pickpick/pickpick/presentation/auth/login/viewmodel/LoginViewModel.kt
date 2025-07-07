package com.pickpick.pickpick.presentation.auth.login.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.util.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginUiState>(LoginUiState()) {

    fun updateEmail(email: String) {
        val enabled = enabledButton(email, _uiState.value.password)
        _uiState.update {
            it.copy(email = email, enabled = enabled)
        }
    }

    fun updatePassword(password: String) {
        val enabled = enabledButton(_uiState.value.email, password)
        _uiState.update {
            it.copy(password = password, enabled = enabled)
        }
    }

    private fun enabledButton(email: String, password: String): Boolean =
        EmailValidator.isValidEmailBasic(email) && password.isNotEmpty()

    fun login() {
        _uiState.update {
            it.copy(errorText = "존재하지 않는 ID, 혹은 비밀번호가 틀렸습니다.")
        }
    }
}
