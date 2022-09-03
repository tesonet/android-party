package com.yasserakbbach.androidparty.util

import android.util.Log
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenHeaderInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository,
) : Interceptor {

    private val token by lazy {
        runBlocking { sessionRepository.getSession().first().token }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_AUTHORIZATION, token ?: "")
            .build()
        Log.d("AUTHORIZATION_HEADER", "intercept: header token $token")
        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}