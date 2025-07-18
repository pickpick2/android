package com.pickpick.pickpick.data.websocket

import com.pickpick.pickpick.domain.websocket.model.StompMessage
import com.pickpick.pickpick.domain.websocket.repository.WebSocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WebSocketRepositoryImpl @Inject constructor(
    private val client: StompWebSocketClient
) : WebSocketRepository {

    override fun connect(roomId: String, token: String) {
        client.connect(roomId, token)
    }

    override fun disconnect() {
        client.disconnect()
    }

    override fun sendMessage(destination: String, payload: Map<String, Any>) {
        client.send(destination, payload)
    }

    override fun observeMessages(): Flow<StompMessage> {
        return client.messageFlow
    }
}
