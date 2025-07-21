package com.pickpick.pickpick.data.pick.remote.repository

import com.pickpick.pickpick.core.data.remote.getOrThrow
import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.core.result.safeApiCall
import com.pickpick.pickpick.data.pick.remote.api.PickApi
import com.pickpick.pickpick.data.pick.remote.dto.toDomain
import com.pickpick.pickpick.domain.pick.model.Room
import com.pickpick.pickpick.domain.pick.repository.PickRepository
import javax.inject.Inject

class PickRepositoryImpl @Inject constructor(
    private val api: PickApi
): PickRepository{

    override suspend fun createRoom(roomCapacity: Int): ApiResult<Room> = safeApiCall {
        val body = mapOf("roomCapacity" to roomCapacity)
        api.createRoom(body).getOrThrow { it.toDomain() }
    }

}