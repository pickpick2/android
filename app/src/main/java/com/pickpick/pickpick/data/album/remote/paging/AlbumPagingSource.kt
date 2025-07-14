package com.pickpick.pickpick.data.album.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pickpick.pickpick.core.data.remote.getOrThrow
import com.pickpick.pickpick.data.album.remote.api.AlbumApi
import com.pickpick.pickpick.data.album.remote.dto.toDomain
import com.pickpick.pickpick.domain.album.model.Photo

class AlbumPagingSource(
    private val api: AlbumApi,
    private val search: String?,
    private val cursor: String?, // 정렬 기준
    private val size: Int
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 0

            val response = api.getAlbums(
                search = search,
                cursor = cursor,
                size = size,
                page = page
            )

            val photos = response.getOrThrow { it.map { dto -> dto.toDomain() } }

            LoadResult.Page(
                data = photos,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (photos.size < size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}
