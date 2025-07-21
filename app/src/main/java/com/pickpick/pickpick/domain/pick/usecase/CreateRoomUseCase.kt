package com.pickpick.pickpick.domain.pick.usecase

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.pick.model.Room
import com.pickpick.pickpick.domain.pick.repository.PickRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val repository: PickRepository
) {
    suspend operator fun invoke(roomCapacity: Int): ApiResult<Room> {
        return repository.createRoom(roomCapacity)
    }
}