/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.remote.api

import com.baruckis.androidparty.data.repository.LocalDataSource
import com.baruckis.androidparty.remote.API_SERVICE_AUTHENTICATION_KEY_START
import com.baruckis.androidparty.remote.API_SERVICE_AUTHENTICATION_NAME
import com.baruckis.androidparty.remote.API_SERVICE_CONTENT_TYPE_KEY
import com.baruckis.androidparty.remote.API_SERVICE_CONTENT_TYPE_NAME
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor used to intercept the actual request and
 * to supply your API Key in REST API calls via a custom header.
 */
class AuthenticationInterceptor(private val localDataSource: LocalDataSource) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(API_SERVICE_CONTENT_TYPE_NAME, API_SERVICE_CONTENT_TYPE_KEY)
            .addHeader(
                API_SERVICE_AUTHENTICATION_NAME,
                API_SERVICE_AUTHENTICATION_KEY_START + localDataSource.getToken()
            )
            .build()

        return chain.proceed(newRequest)
    }

}