package com.pickpick.pickpick.core.exception

class GlobalException(
    override val message: String,
    val code: Int? = null
) : RuntimeException(message)
