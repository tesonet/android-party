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
import com.baruckis.androidparty.remote.API_TESONET_SERVICE_BASE_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TesonetApiServiceFactory {

    fun createTesonetApiService(
        isDebug: Boolean,
        localDataSource: LocalDataSource
    ): TesonetApiService {
        return createRetrofit(
            API_TESONET_SERVICE_BASE_URL,
            createOkHttpClient(
                isDebug,
                localDataSource
            ),
            Gson()
        ).create(TesonetApiService::class.java)
    }

    private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun createOkHttpClient(
        isDebug: Boolean,
        localDataSource: LocalDataSource
    ): OkHttpClient {
        return OkHttpClient.Builder()
            /**
             * It will add authentication headers to every call we make.
             */
            .addInterceptor(
                AuthenticationInterceptor(
                    localDataSource
                )
            )
            /**
             * Log requests and responses.
             * Add logging as the last interceptor, because this will also log the information
             * which you added or manipulated with previous interceptors to your request.
             */
            .addInterceptor(
                createHttpLoggingInterceptor(
                    isDebug
                )
            )
            .build()
    }

    private fun createHttpLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}