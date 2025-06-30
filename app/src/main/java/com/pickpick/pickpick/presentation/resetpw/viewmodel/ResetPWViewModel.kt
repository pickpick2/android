package com.pickpick.pickpick.presentation.resetpw.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.util.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ResetPWViewModel @Inject constructor() : BaseViewModel<ResetPWUiState>(ResetPWUiState()) {
    fun updatePassword(password: String) {
        val enabled = enabledButton(
            password, _uiState.value.checkPassword,
        )
        _uiState.update {
            it.copy(password = password, enabled = enabled)
        }
    }

    fun updateCheckPassword(checkPassword: String) {
        val enabled = enabledButton(
            _uiState.value.password, checkPassword,
        )
        _uiState.update {
            it.copy(checkPassword = checkPassword, enabled = enabled)
        }
    }

    fun resetPassword() {
        // 유효성 검사 실패시 초기화 요청 X
        if (!validatePassword()) {
            return
        }


    }


    private fun enabledButton(
        password: String, checkPassword: String
    ): Boolean =
        password.isNotEmpty() && checkPassword.isNotEmpty()

    private fun validatePassword(): Boolean {
        // 비밀번호가 같은지 확인
        if (_uiState.value.password != _uiState.value.checkPassword) {
            _uiState.update {
                it.copy(checkPasswordErrorText = "비밀번호가 일치하지 않습니다.")
            }
            return false
        } else {
            _uiState.update {
                it.copy(checkPasswordErrorText = null)
            }
        }

        // 비밀번호 정규 표현식이 맞는지 확인
        if (PasswordValidator.isValidPassword(_uiState.value.password)) {
            _uiState.update {
                it.copy(passwordErrorText = null)
            }
        } else {
            _uiState.update {
                it.copy(passwordErrorText = "영문자, 숫자, 특수문자(!, @, #, \$, %, ^, &) 를 조합하여 8자 이상 15자 이하로 설정해 주세요.")
            }
            return false
        }

        return true
    }

}
