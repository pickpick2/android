package com.pickpick.pickpick.domain.album.usecase

import androidx.paging.PagingData
import com.pickpick.pickpick.domain.album.model.Photo
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(
        search: String?,
        cursor: String?,
        size: Int = 20
    ): Flow<PagingData<Photo>> {
        return repository.getPagedAlbums(
            search = search,
            cursor = cursor,
            size = size
        )
    }
}