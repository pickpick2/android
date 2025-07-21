package com.pickpick.pickpick.data.auth.remote.dto

import com.pickpick.pickpick.domain.auth.model.Guest

data class GuestLoginResponse(
    val memberId: Long = 0L,
    val accessToken: String = "",
)

fun GuestLoginResponse.toDomain(): Guest = Guest(
    memberId = this.memberId, accessToken = this.accessToken
)
