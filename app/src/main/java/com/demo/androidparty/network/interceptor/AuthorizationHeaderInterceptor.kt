package com.demo.androidparty.network.interceptor

import com.demo.androidparty.storage.preferences.AppPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor(
    private val preferences: AppPreferences
) : Interceptor {

    private val token by lazy {
        "Bearer ${preferences.getToken()}"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_AUTHORIZATION, token)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}