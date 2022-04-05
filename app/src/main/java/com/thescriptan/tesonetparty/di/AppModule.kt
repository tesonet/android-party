package com.thescriptan.tesonetparty.di

import android.content.Context
import com.thescriptan.tesonetparty.login.repository.LoginRepository
import com.thescriptan.tesonetparty.login.repository.LoginRepositoryImpl
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.network.LoginApi
import com.thescriptan.tesonetparty.storage.TesoDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @Singleton
    @Provides
    fun provideLoginApi(): LoginApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://playground.tesonet.lt/v1/")
        .build()
        .create()

    @Singleton
    @Provides
    fun provideLoginRepository(api: LoginApi, dataStore: TesoDataStore): LoginRepository =
        LoginRepositoryImpl(api, dataStore)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): TesoDataStore =
        TesoDataStore(context)
}
