package com.pickpick.pickpick.domain.pick.repository

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.pick.model.Room

interface PickRepository {
    suspend fun createRoom(roomCapacity: Int): ApiResult<Room>
}