package com.pickpick.pickpick.core.util

import android.util.Patterns

object EmailValidator {
    fun isValidEmailBasic(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
