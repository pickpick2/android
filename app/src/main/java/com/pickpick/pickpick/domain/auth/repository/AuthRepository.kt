package com.pickpick.pickpick.domain.auth.repository

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.auth.model.Guest

interface AuthRepository {
    suspend fun guestLogin(): ApiResult<Guest>
}
