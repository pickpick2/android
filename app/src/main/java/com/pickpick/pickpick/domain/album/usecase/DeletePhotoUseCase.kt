package com.pickpick.pickpick.domain.album.usecase

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.album.repository.AlbumRepository
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(albumId: String): ApiResult<Unit> {
        return repository.deletePhoto(albumId)
    }
}