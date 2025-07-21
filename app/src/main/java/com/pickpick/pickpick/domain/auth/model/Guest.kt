package com.pickpick.pickpick.domain.auth.model

data class Guest(
    val memberId: Long = 0L,
    val accessToken: String = "",
)
