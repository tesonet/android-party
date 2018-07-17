package levkovskiy.dev.tesonettest.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
    .client(makeHttpClient(interceptors))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("http://playground.tesonet.lt/v1/")
    .build()

private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(headersInterceptor())
    .apply { interceptors().addAll(interceptors) }
    .build()

fun accessTokenProvidingInterceptor(token: String) = Interceptor { chain ->
  chain.proceed(
      chain.request().newBuilder()
          .addHeader("Authorization", "Bearer $token")
          .build()
  )
}

fun headersInterceptor() = Interceptor { chain ->
  chain.proceed(
      chain.request().newBuilder()
          .addHeader("Accept", "application/json")
          .addHeader("Accept-Language", "en")
          .addHeader("Content-Type", "application/json")
          .build()
  )
}
