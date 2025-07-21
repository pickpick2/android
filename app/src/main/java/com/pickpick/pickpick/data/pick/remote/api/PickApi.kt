package com.pickpick.pickpick.data.pick.remote.api

import com.pickpick.pickpick.core.data.remote.BaseResponse
import com.pickpick.pickpick.data.pick.remote.dto.RoomResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PickApi {
    @POST("room")
    suspend fun createRoom(@Body map: Map<String, Int>): BaseResponse<RoomResponse>
}