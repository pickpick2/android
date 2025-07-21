package com.pickpick.pickpick.core.data.remote

import com.pickpick.pickpick.core.exception.GlobalException


data class BaseResponse<T>(
    val code: Int, val message: String, val data: T?
)

// 비즈니스 로직 상 성공 코드: 200
inline fun <T, R> BaseResponse<T>.getOrThrow(transform: (T) -> R): R {
    if (code == 200 && data != null) {
        return transform(data)
    } else {
        throw GlobalException(message)
    }
}

inline fun <T> BaseResponse<T>.getOrThrowNull(onSuccess: () -> T): T {
    if (code == 200) return onSuccess()
    else throw GlobalException(message)
}
