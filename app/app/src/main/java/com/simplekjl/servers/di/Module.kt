package com.simplekjl.servers.di

import android.annotation.SuppressLint
import com.simplekjl.data.client.ClientService
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.servers.BuildConfig
import com.simplekjl.servers.framework.AuthInterceptor
import com.simplekjl.servers.navigation.Navigator
import com.simplekjl.servers.storage.SessionManagerImpl
import com.simplekjl.servers.ui.list.ServerListViewModel
import com.simplekjl.servers.ui.login.LoginViewModel
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val mainModule = createMainModule() // UI level


@SuppressLint("UnspecifiedImmutableFlag")
private fun createMainModule() = module {
    single {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(0, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)
        builder.addInterceptor(AuthInterceptor(get()))
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(interceptor)
        }

        val client = builder.build()

        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    single<ClientService> { get<Retrofit>().create(ClientService::class.java) }
    single<SessionManager> { SessionManagerImpl(androidContext()) }
    factory { AuthInterceptor(get()) }
    single { Navigator() }
    factory { LoginViewModel(get(), get(), get()) }
    factory { ServerListViewModel(get(), get(), get()) }
}
