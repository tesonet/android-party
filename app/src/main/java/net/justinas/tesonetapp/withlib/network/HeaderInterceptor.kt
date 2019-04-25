package net.justinas.tesonetapp.withlib.network

import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor(var token: String? = null) : Interceptor {

    private fun isLoginCall(original: Request) = original.url().toString().contains(TesonetApi.TOKEN_URL)

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")


        if (!isLoginCall(original)) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}