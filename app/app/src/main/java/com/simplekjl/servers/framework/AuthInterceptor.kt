package com.simplekjl.servers.framework

import com.simplekjl.domain.repository.SessionManager
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Chain): Response {
        var request = chain.request()
        if (request.header("No-Authentication") == null) {
            request = request.newBuilder()
                .addHeader("Authorization", sessionManager.fetchAuthToken()).build()
        }
        return chain.proceed(request)
    }
}
