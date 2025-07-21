package com.pickpick.pickpick.presentation.auth.start.viewmodel

import androidx.lifecycle.viewModelScope
import com.pickpick.pickpick.core.data.TokenDataStore
import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.result.ResultState
import com.pickpick.pickpick.core.result.toResultState
import com.pickpick.pickpick.domain.auth.usecase.GuestLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val guestLoginUseCase: GuestLoginUseCase,
    private val tokenDataStore: TokenDataStore,
) : BaseViewModel<GuestUiState>(GuestUiState()) {

    fun guestLogin() {
        viewModelScope.launch {
            val result = guestLoginUseCase().toResultState()

            when (result) {
                is ResultState.Success -> {
                    tokenDataStore.saveAccessToken(result.data.accessToken)
                    _uiState.update {
                        it.copy(guestLoginResult = GuestLoginResult.Success(result.data.accessToken))
                    }
                }

                is ResultState.Error -> _uiState.update {
                    it.copy(guestLoginResult = GuestLoginResult.Error(result.messageRes))
                }

                ResultState.Loading -> _uiState.update {
                    it.copy(guestLoginResult = GuestLoginResult.Loading)
                }
            }


        }
    }
}
