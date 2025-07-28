package com.pickpick.pickpick.data.websocket

import android.util.Log
import com.pickpick.pickpick.domain.websocket.model.StompMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

import io.reactivex.disposables.CompositeDisposable

class StompWebSocketClient {

    private lateinit var stompClient: StompClient
    private val _messageFlow = MutableSharedFlow<StompMessage>()
    val messageFlow: SharedFlow<StompMessage> = _messageFlow
    private val disposables = CompositeDisposable()

    fun connect(roomId: Int, token: String) {
        val url = "ws://54.91.200.235:8080/wss/connection"
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

        val headers = listOf(
            StompHeader("Authorization", "Bearer $token"),
            StompHeader("roomId", roomId.toString())
        )

        disposables.add(
            stompClient.lifecycle().subscribe { event ->
                when (event.type) {
                    LifecycleEvent.Type.OPENED -> {
                        Log.d("WebSocket", "연결됨")
                        subscribe("/topic/room/$roomId")
                        subscribe("/user/queue")
                    }
                    LifecycleEvent.Type.ERROR -> {
                        Log.e("WebSocket", "오류", event.exception)
                    }
                    LifecycleEvent.Type.CLOSED -> {
                        Log.d("WebSocket", "연결 종료")
                    }
                    else -> {}
                }
            }
        )

        stompClient.connect(headers)
    }

    private fun subscribe(topic: String) {
        disposables.add(
            stompClient.topic(topic).subscribe { message ->
                try {
                    val payload = JSONObject(message.payload)
                    val type = payload.getString("type")
                    val dataJson = payload.getJSONObject("data")

                    val dataMap = mutableMapOf<String, Any>()
                    dataJson.keys().forEach { key ->
                        dataMap[key] = dataJson.get(key)
                    }

                    _messageFlow.tryEmit(StompMessage(type, dataMap))
                } catch (e: Exception) {
                    Log.e("WebSocket", "메시지 파싱 오류", e)
                }
            }
        )
    }

    fun send(destination: String, payload: Map<String, Any>) {
        val json = JSONObject(payload)
        disposables.add(stompClient.send(destination, json.toString()).subscribe())
    }

    fun disconnect() {
        if (::stompClient.isInitialized) {
            stompClient.disconnect()
            disposables.clear()
        }
    }

}

