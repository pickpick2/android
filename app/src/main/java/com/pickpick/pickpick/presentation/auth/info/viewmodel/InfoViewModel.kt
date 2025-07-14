package com.pickpick.pickpick.presentation.auth.info.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor() : BaseViewModel<InfoUiState>(InfoUiState()) {

    // TODO: 프로필 사진 추가

    fun updateNickname(nickname: String) {
        val enabled = enabledButton(nickname)

        _uiState.update {
            it.copy(nickname = nickname, enabled = enabled)
        }
    }

    private fun enabledButton(
        nickname: String
    ): Boolean =
        nickname.isNotEmpty()

    fun complete() {
        _uiState.update {
            it.copy(nicknameErrorText = "중복된 닉네임 입니다.")
        }

    }

}