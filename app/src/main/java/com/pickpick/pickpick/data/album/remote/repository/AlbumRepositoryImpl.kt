package com.pickpick.pickpick.data.album.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pickpick.pickpick.core.data.remote.getOrThrow
import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.core.result.safeApiCall
import com.pickpick.pickpick.data.album.remote.api.AlbumApi
import com.pickpick.pickpick.data.album.remote.dto.toDomain
import com.pickpick.pickpick.data.album.remote.paging.AlbumPagingSource
import com.pickpick.pickpick.domain.album.model.Photo
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApi
): AlbumRepository {
    override suspend fun getAlbums(
        search: String?,
        cursor: String?,
        size: Int
    ): ApiResult<List<Photo>> = safeApiCall {
        api.getAlbums(search, cursor, size).getOrThrow { it.map { data -> data.toDomain() } }
    }

    override suspend fun getPhoto(albumId: String): ApiResult<Photo> = safeApiCall {
        api.getPhoto(albumId).getOrThrow { it.toDomain() }
    }

    override suspend fun deletePhoto(albumId: String): ApiResult<Unit> = safeApiCall {
        api.deletePhoto(albumId).getOrThrow { }
    }

    override fun getPagedAlbums(
        search: String?,
        cursor: String?,
        size: Int
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = size),
            pagingSourceFactory = {
                AlbumPagingSource(
                    api = api,
                    search = search,
                    cursor = cursor,
                    size = size
                )
            }
        ).flow
    }

}