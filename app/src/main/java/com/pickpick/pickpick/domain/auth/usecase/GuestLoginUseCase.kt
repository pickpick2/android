package com.pickpick.pickpick.domain.auth.usecase

import com.pickpick.pickpick.core.result.ApiResult
import com.pickpick.pickpick.domain.auth.model.Guest
import com.pickpick.pickpick.domain.auth.repository.AuthRepository
import javax.inject.Inject

class GuestLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): ApiResult<Guest> = repository.guestLogin()
}
