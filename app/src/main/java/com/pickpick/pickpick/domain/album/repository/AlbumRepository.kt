package com.pickpick.pickpick.domain.album.repository

import androidx.paging.PagingData
import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.album.model.Photo
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(
        search: String? = null,
        cursor: String? = null,
        size: Int = 20
    ): ApiResult<List<Photo>>

    suspend fun getPhoto(albumId: String): ApiResult<Photo>

    suspend fun deletePhoto(albumId: String): ApiResult<Unit>

    fun getPagedAlbums(
        search: String? = null,
        cursor: String? = null,
        size: Int = 20
    ): Flow<PagingData<Photo>>
}