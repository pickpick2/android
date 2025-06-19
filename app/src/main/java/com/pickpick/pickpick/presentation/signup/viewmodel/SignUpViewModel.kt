package com.pickpick.pickpick.presentation.signup.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.util.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel<SignUpUiState>(SignUpUiState()) {
    fun updateEmail(email: String) {
        val enabled = enabledButton(
            email, _uiState.value.password, _uiState.value.checkPassword, _uiState.value.checkPolicy
        )
        _uiState.update {
            it.copy(email = email, enabled = enabled)
        }
    }

    fun updatePassword(password: String) {
        val enabled = enabledButton(
            _uiState.value.email, password, _uiState.value.checkPassword, _uiState.value.checkPolicy
        )
        _uiState.update {
            it.copy(password = password, enabled = enabled)
        }
    }

    fun updateCheckPassword(checkPassword: String) {
        val enabled = enabledButton(
            _uiState.value.email, _uiState.value.password, checkPassword, _uiState.value.checkPolicy
        )
        _uiState.update {
            it.copy(checkPassword = checkPassword, enabled = enabled)
        }
    }

    fun updateCheckPolicy(checkPolicy: Boolean) {
        val enabled = enabledButton(
            _uiState.value.email, _uiState.value.password, _uiState.value.checkPassword, checkPolicy
        )
        _uiState.update {
            it.copy(checkPolicy = checkPolicy, enabled = enabled)
        }
    }

    private fun enabledButton(
        email: String, password: String, checkPassword: String, checkPolicy: Boolean
    ): Boolean =
        EmailValidator.isValidEmailBasic(email) && password.isNotEmpty() && password == checkPassword && checkPolicy

    fun signUp() {
        _uiState.update {
            it.copy(emailErrorText = "이메일 형식이 올바르지 않습니다.", passwordErrorText = "비밀번호가 일치하지 않습니다.")
        }

    }


}
