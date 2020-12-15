package com.example.android_party.injection

import com.example.android_party.BuildConfig
import com.example.android_party.api.TesonetApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Tesonet service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Tesonet service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTesonetApi(retrofit: Retrofit): TesonetApi {
        return retrofit.create(TesonetApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .enableComplexMapKeySerialization()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}