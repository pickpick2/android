package com.pickpick.pickpick.core.util

import android.util.Patterns


/**
 * 이메일 유효성 검사
 */
object EmailValidator {
    fun isValidEmailBasic(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

/**
 * 비밀번호 유효성 검증
 */
object PasswordValidator {
    // 영문자, 숫자, 특수문자 조합 8-15자
    private const val PASSWORD_PATTERN =
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&])[A-Za-z\\d!@#\$%^&]{8,15}\$"
    private val passwordRegex = Regex(PASSWORD_PATTERN)

    fun isValidPassword(password: String): Boolean {
        return password.matches(passwordRegex)
    }
}
