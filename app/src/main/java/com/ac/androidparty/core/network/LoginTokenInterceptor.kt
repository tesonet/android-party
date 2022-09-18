package com.ac.androidparty.core.network

import com.ac.androidparty.login.domain.preferences.LoginPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class LoginTokenInterceptor @Inject constructor(
    private val loginPreferences: LoginPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        with(chain.request()) {
            when (header(NetworkConstants.REQUIRE_AUTHORIZATION)) {
                NetworkConstants.LOGIN_TOKEN -> {
                    chain.proceedWith {
                        removeHeader(NetworkConstants.REQUIRE_AUTHORIZATION)
                        addHeader(
                            name = AUTHORIZATION,
                            value = LOGIN_BEARER_TOKEN.replace(
                                REPLACEMENT, loginPreferences.token ?: ""
                            )
                        )
                    }
                }
                else -> chain.proceed(this)
            }
        }

    private companion object {
        const val REPLACEMENT = "%s"
        const val AUTHORIZATION = "Authorization"
        const val LOGIN_BEARER_TOKEN = "Bearer $REPLACEMENT"
    }
}