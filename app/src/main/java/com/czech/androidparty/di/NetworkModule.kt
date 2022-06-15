package com.czech.androidparty.di

import com.czech.androidparty.connection.NetworkConnection
import com.czech.androidparty.datasource.network.ApiService
import com.czech.androidparty.datasource.network.ApiServiceImpl
import com.czech.androidparty.utils.Routes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            defaultRequest {
                if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(
        client: HttpClient
    ): ApiService {
        return ApiServiceImpl(
            client = client,
            baseUrl = Routes.BASE_URL
        )
    }

    @Provides
    @Singleton
    fun provideNetworkConnection(
        context: BaseApplication
    ): NetworkConnection {
        return NetworkConnection(context)
    }
}