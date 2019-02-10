package lt.petraslabutis.tesonetapplication.dagger

import com.securepreferences.SecurePreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import lt.petraslabutis.tesonetapplication.ApiInterceptor
import lt.petraslabutis.tesonetapplication.BuildConfig
import lt.petraslabutis.tesonetapplication.api.AppService
import lt.petraslabutis.tesonetapplication.api.AuthenticationService
import lt.petraslabutis.tesonetapplication.managers.AuthenticationManager
import lt.petraslabutis.tesonetapplication.managers.ServerDataManager
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
    internal fun provideAuthenticationManager(retrofit: Retrofit, preferences: SecurePreferences) =
        AuthenticationManager(retrofit.create(AuthenticationService::class.java), preferences)

    @Provides
    @Singleton
    internal fun provideServerDataManager(retrofit: Retrofit) =
        ServerDataManager(retrofit.create(AppService::class.java))

}