package com.pickpick.pickpick.domain.websocket.repository

import com.pickpick.pickpick.domain.websocket.model.StompMessage
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {
    fun connect(roomId: String, token: String)
    fun disconnect()
    fun sendMessage(destination: String, payload: Map<String, Any>)
    fun observeMessages(): Flow<StompMessage>
}