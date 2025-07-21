package com.pickpick.pickpick.data.auth.remote.api

import com.pickpick.pickpick.core.data.remote.BaseResponse
import com.pickpick.pickpick.data.auth.remote.dto.GuestLoginResponse
import retrofit2.http.POST

interface AuthApi {
    @POST("v1/guest")
    suspend fun guestLogin(): BaseResponse<GuestLoginResponse>
}
