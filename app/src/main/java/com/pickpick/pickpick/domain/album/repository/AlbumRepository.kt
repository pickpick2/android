package com.pickpick.pickpick.domain.album.repository

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.album.model.Photo

interface AlbumRepository {
    suspend fun getAlbums(
        query: String? = null,
        cursor: String? = null,
        size: Int = 20
    ): ApiResult<List<Photo>>

    suspend fun getPhoto(albumId: String): ApiResult<Photo>

    suspend fun deletePhoto(albumId: String): ApiResult<Unit>
}