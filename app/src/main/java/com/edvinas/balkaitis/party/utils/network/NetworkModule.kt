package com.edvinas.balkaitis.party.utils.network

import com.edvinas.balkaitis.party.BuildConfig
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.ServersService
import com.edvinas.balkaitis.party.utils.schedulers.Io
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Module
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER = "Bearer"

        @NoAuth
        @JvmStatic
        @Singleton
        @Provides
        fun provideNoAuthRetrofit(@NoAuth client: OkHttpClient, @Io scheduler: Scheduler): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                .build()
        }

        @Auth
        @JvmStatic
        @Singleton
        @Provides
        fun provideAuthRetrofit(@Auth client: OkHttpClient, @Io scheduler: Scheduler): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
                .build()
        }

        @NoAuth
        @JvmStatic
        @Provides
        fun provideNoAuthOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        @Auth
        @JvmStatic
        @Provides
        fun provideAuthOkHttpClient(tokenStorage: TokenStorage): OkHttpClient {
            val token = tokenStorage.getToken()
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request()
                            .newBuilder()
                            .header(AUTHORIZATION_HEADER, "$BEARER $token")
                            .build()
                    )
                }
                .build()
        }

        @JvmStatic @Provides
        fun provideLoginService(@NoAuth retrofit: Retrofit): LoginService {
            return retrofit.create(LoginService::class.java)
        }

        @JvmStatic
        @Provides
        fun provideServersService(@Auth retrofit: Retrofit): ServersService {
            return retrofit.create(ServersService::class.java)
        }
    }
}
