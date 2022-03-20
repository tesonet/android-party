package com.example.domain_server.data.interceptor

import com.example.domain_login.data.datastore.LocalSharedPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    private val preferences: LocalSharedPreference
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