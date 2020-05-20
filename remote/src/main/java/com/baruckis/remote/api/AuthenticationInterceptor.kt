package com.baruckis.remote.api

import com.baruckis.data.repository.LocalDataSource
import com.baruckis.remote.API_SERVICE_AUTHENTICATION_KEY_START
import com.baruckis.remote.API_SERVICE_AUTHENTICATION_NAME
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor used to intercept the actual request and
 * to supply your API Key in REST API calls via a custom header.
 */
class AuthenticationInterceptor(private val localDataSource: LocalDataSource) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(
                API_SERVICE_AUTHENTICATION_NAME,
                API_SERVICE_AUTHENTICATION_KEY_START + localDataSource.getToken())
            .build()

        return chain.proceed(newRequest)
    }

}