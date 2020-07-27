package lt.havefun.tesonetfunparty.injections.modules

import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.Config
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        prefsManager: IPreferencesManager
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addNetworkInterceptor{ chain ->
            val initialRequest = chain.request()

            prefsManager.getToken()?.let {
                val request = initialRequest.newBuilder()
                    .header("Authorization", "Bearer $it")
                    .build()
                chain.proceed(request)
            } ?: chain.proceed(initialRequest)
        }

        httpClient
            .retryOnConnectionFailure(true)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        val url = Config.SERVER_URL
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)
}