package com.pickpick.pickpick.presentation.websocket.viewmodel

import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import com.pickpick.pickpick.domain.websocket.model.StompMessage
import com.pickpick.pickpick.domain.websocket.repository.WebSocketRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebSocketViewModel @Inject constructor(
    private val repository: WebSocketRepository
) : ViewModel() {

    private val _messageState = MutableStateFlow<StompMessage?>(null)
    val messageState: StateFlow<StompMessage?> = _messageState

    fun connect(roomId: String, token: String) {
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

    override fun onCleared() {
        repository.disconnect()
        super.onCleared()
    }
}
