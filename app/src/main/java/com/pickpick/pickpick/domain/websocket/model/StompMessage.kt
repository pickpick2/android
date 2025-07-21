package com.pickpick.pickpick.domain.websocket.model

data class StompMessage(
    val type: String,
    val data: Map<String, Any>
)
