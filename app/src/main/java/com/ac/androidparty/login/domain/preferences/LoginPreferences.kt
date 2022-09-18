package com.ac.androidparty.login.domain.preferences

import com.ac.androidparty.core.sharedprefs.DefaultSharedPreferences
import javax.inject.Inject

internal class LoginPreferences @Inject constructor() : DefaultSharedPreferences(
    key = "login_preferences"
) {
    var token by stringPreferences("loginToken")

    val isTokenAvailable: Boolean
        get() = token.isNullOrBlank().not()

    fun invalidateToken(): Boolean {
        token = null
        return token.isNullOrEmpty()
    }
}