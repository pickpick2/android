package com.pickpick.pickpick.presentation.websocket.viewmodel

import com.pickpick.pickpick.domain.websocket.model.StompMessage
import com.pickpick.pickpick.domain.websocket.repository.WebSocketRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private val repository: WebSocketRepository
) : ViewModel() {

    private var roomId: Int? = null

    private val _messageState = MutableStateFlow<StompMessage?>(null)
    val messageState: StateFlow<StompMessage?> = _messageState

    fun connect(roomId: Int, token: String) {
        this.roomId = roomId
        repository.connect(roomId, token)
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            repository.observeMessages().collect {
                _messageState.value = it
            }
        }
    }

    fun send(destination: String, payload: Map<String, Any>) {
        repository.sendMessage(destination, payload)
    }

    // 배경 선택
    fun sendBackgroundUpdate(backgroundId: Int) {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/background"
        val payload = mapOf("backgroundId" to backgroundId)

        send(destination, payload)
    }

    // 멤버 준비 상태 변경
    fun sendMemberStateUpdate(memberReadyState: String) {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/member/state"
        val payload = mapOf("memberReadyState" to memberReadyState)

        send(destination, payload)
    }

    override fun onCleared() {
        repository.disconnect()
        super.onCleared()
    }
}

