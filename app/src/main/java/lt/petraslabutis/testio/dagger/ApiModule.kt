package lt.petraslabutis.testio.dagger

import com.securepreferences.SecurePreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import lt.petraslabutis.testio.ApiInterceptor
import lt.petraslabutis.testio.BuildConfig
import lt.petraslabutis.testio.api.AppService
import lt.petraslabutis.testio.api.AuthenticationService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(preferences: SecurePreferences): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.valueOf(BuildConfig.API_LOGGING_LEVEL)
            }
        ).addInterceptor(ApiInterceptor(preferences))

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideAuthenticationService(retrofit: Retrofit) = retrofit.create(AuthenticationService::class.java)

    @Provides
    @Singleton
    internal fun provideAppService(retrofit: Retrofit) = retrofit.create(AppService::class.java)

}