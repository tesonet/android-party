package app.data.api

import com.k4dima.party.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {
    @Singleton
    @JvmStatic
    @Provides
    fun tesonetService(): TesonetService = Retrofit.Builder()
        .baseUrl("https://playground.tesonet.lt/")
        .addConverterFactory(GsonConverterFactory.create())
        .also {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                it.client(client)
            }
        }
        .build()
        .create(TesonetService::class.java)
}