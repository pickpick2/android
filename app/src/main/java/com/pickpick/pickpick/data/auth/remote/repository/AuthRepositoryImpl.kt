package com.pickpick.pickpick.data.auth.remote.repository

import com.pickpick.pickpick.core.data.remote.getOrThrow
import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.core.result.safeApiCall
import com.pickpick.pickpick.data.auth.remote.api.AuthApi
import com.pickpick.pickpick.data.auth.remote.dto.toDomain
import com.pickpick.pickpick.domain.auth.model.Guest
import com.pickpick.pickpick.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun guestLogin(): ApiResult<Guest> {
        return safeApiCall {
            api.guestLogin().getOrThrow { it.toDomain() }
        }
    }
}
