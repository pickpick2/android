package com.pickpick.pickpick.presentation.websocket.viewmodel

import android.system.Os.access
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import com.pickpick.pickpick.domain.websocket.model.StompMessage
import com.pickpick.pickpick.domain.websocket.repository.WebSocketRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickpick.pickpick.core.data.TokenDataStore
import com.pickpick.pickpick.core.result.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val repository: WebSocketRepository
) : ViewModel() {

    private val access = tokenDataStore.accessTokenFlow
    private var roomId: Int? = null

    private val _messageState = MutableStateFlow<StompMessage?>(null)
    val messageState: StateFlow<StompMessage?> = _messageState

    fun connect(roomId: Int) {
        this.roomId = roomId
        repository.connect(roomId, access.toString())
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            repository.observeMessages().collect {
                _messageState.value = it
            }
        }
    }

    fun send(destination: String, payload: Map<String, Any> = emptyMap()) {
        repository.sendMessage(destination, payload)
    }

    // 방의 멤버 조회
    fun getMembers() {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/members"

        send(destination)
    }

    // 멤버 준비 상태 변경
    fun sendMemberStateUpdate(memberReadyState: String) {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/member/state"
        val payload = mapOf("memberReadyState" to memberReadyState)

        send(destination, payload)
    }

    // 준비 완료 후 시작
    fun sendStart() {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/page"

        send(destination)
    }

    // 배경 선택
    fun sendBackgroundUpdate(backgroundId: Int) {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/background"
        val payload = mapOf("backgroundId" to backgroundId)

        send(destination, payload)
    }

    // 방 인원 수 변경
    fun sendCapacityUpdate(roomCapacity: Int) {
        val currentRoomId = roomId ?: return
        val destination = "/app/room/$currentRoomId/capacity"
        val payload = mapOf("roomCapacity" to roomCapacity)

        send(destination, payload)
    }

    override fun onCleared() {
        repository.disconnect()
        super.onCleared()
    }
}

