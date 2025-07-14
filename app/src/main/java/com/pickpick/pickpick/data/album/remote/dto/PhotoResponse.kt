package com.pickpick.pickpick.data.album.remote.dto

import com.pickpick.pickpick.domain.album.model.Photo

data class PhotoResponse(
    val photoId: String = "",
    val title: String = "",
    val url: String = "",
    val createdAt: String = "",
    val participantIds: List<Int> = emptyList()
)

fun PhotoResponse.toDomain(): Photo {
    return Photo(
        photoId = this.photoId,
        title = this.title,
        url = this.url,
        createdAt = this.createdAt,
        participantIds = this.participantIds
    )
}
