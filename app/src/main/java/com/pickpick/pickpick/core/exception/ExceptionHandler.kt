package com.pickpick.pickpick.core.exception

import com.pickpick.pickpick.core.error.AppError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ExceptionHandler {
    fun map(e: Throwable): AppError {
        return when (e) {
            is IOException -> AppError.Network
            is SocketTimeoutException -> AppError.Timeout
            is HttpException -> {
                when (e.code()) {
                    401 -> AppError.Unauthorized
                    else -> AppError.Unknown(e)
                }
            }
            else -> AppError.Unknown(e)
        }
    }
}
