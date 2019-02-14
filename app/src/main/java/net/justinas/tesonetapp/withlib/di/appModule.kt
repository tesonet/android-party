package net.justinas.tesonetapp.withlib.di

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import com.squareup.moshi.Moshi
import net.justinas.tesonetapp.withlib.BuildConfig
import net.justinas.tesonetapp.withlib.domain.remote.TesonetApi
import net.justinas.tesonetapp.withlib.domain.remote.Token
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


val appModule: Module = module {

    single {

        var token: String? = null
        val TIMEOUT: Long = 60
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")


                if (!isLoginCall(original)){
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }

                val request = requestBuilder.build()
                val proceed = chain.proceed(request)

                if (isLoginCall(request)){
                    val value = Moshi.Builder().build().adapter(Token::class.java).fromJson(proceed.peekBody(1024).source())?.token
                    token = value
                }
                return proceed
            }

            private fun isLoginCall(original: Request) = original.url().toString().contains(TesonetApi.TOKEN_URL)
        })

        httpClient.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        (get() as Retrofit).create(TesonetApi::class.java)
    }
}