package com.jonastiskus.testio.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jonastiskus.testio.auth.AuthService
import com.jonastiskus.testio.network.service.ApiService
import com.jonastiskus.testio.network.service.ServiceProvider
import com.jonastiskus.testio.repository.room.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceFactory(private val context: Context) : ServiceProvider {

    private companion object val BASE_URL = "http://playground.tesonet.lt/v1/"


    override fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    override fun getAuthService(): AuthService {
        return AuthService(context, getApiService())
    }

    private fun getOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    override fun getAppDatabase(): AppDatabase {
        return AppDatabase.getDatabase(context)
    }


}