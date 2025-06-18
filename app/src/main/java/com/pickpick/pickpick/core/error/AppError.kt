package com.pickpick.pickpick.core.error

import androidx.annotation.StringRes
import com.pickpick.pickpick.R

sealed class AppError(@StringRes val messageRes: Int) {
    object Network : AppError(R.string.error_network)
    object Timeout : AppError(R.string.error_timeout)
    object Unauthorized : AppError(R.string.error_unauthorized)
    data class Unknown(val throwable: Throwable? = null) : AppError(R.string.error_unknown)
}
