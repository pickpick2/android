package com.pickpick.pickpick.domain.album.usecase

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.album.model.Photo
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(
        query: String?,
        cursor: String?,
        size: Int
    ): ApiResult<List<Photo>> {
        return repository.getAlbums(query, cursor, size)
    }
}