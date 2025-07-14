package com.pickpick.pickpick.domain.album.usecase

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.album.model.Photo
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(albumId: String): ApiResult<Photo> {
        return repository.getPhoto(albumId)
    }
}