package com.pickpick.pickpick.domain.album.model

data class Photo(
    val photoId: String = "",
    val title: String = "",
    val url: String = "",
    val createdAt: String = "",
    val participantIds: List<Int> = emptyList()
)
