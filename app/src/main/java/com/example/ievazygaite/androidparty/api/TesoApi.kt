package com.example.ievazygaite.androidparty.api

import com.example.ievazygaite.androidparty.data.login.LoginBody
import com.example.ievazygaite.androidparty.data.login.LoginResponse
import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.utils.BASE_URL
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TesoApi {

    companion object Factory {

        fun create(): TesoApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Accept-Language", "en")
                            .addHeader("Content-Type", "application/json")
                            .build()
                    )
                }
                    .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(TesoApi::class.java)
        }
    }

    @POST("tokens")
    fun authUser(@Body userBody: LoginBody): Single<LoginResponse>


    @GET("servers")
    fun getServers(@Header("Authorization") bearerToken: String): Single<List<Server>>
}