package com.example.androidParty.dagger

import android.content.Context
import com.example.androidParty.datalayer.UserPreferences
import com.example.androidParty.datalayer.network.NordApiService
import com.example.androidParty.datalayer.repository.LoginRepositoryImpl
import com.example.androidParty.datalayer.repository.ServerRepositoryImpl
import com.example.androidParty.presentation.login.LoginRepository
import com.example.androidParty.presentation.serverList.ServerRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * retrofit need 2 things in order to work :
     * 1- Base Url
     * 2- Converter factory which is here Moshi...
     */
    private const val BASE_URL = "https://playground.tesonet.lt/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideNordApi(retrofit: Retrofit): NordApiService =
        retrofit.create(NordApiService::class.java)

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences =
        UserPreferences(context)

    @Singleton
    @Provides
    fun provideLoginRepository(
        nordApiService: NordApiService,
        userPreferences: UserPreferences
    ): LoginRepository =
        LoginRepositoryImpl(userPreferences, nordApiService)

    @Singleton
    @Provides
    fun provideServerRepository(
        nordApiService: NordApiService
    ): ServerRepository =
        ServerRepositoryImpl(nordApiService)
}