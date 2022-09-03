package com.yasserakbbach.androidparty.login.data.di

import android.content.Context
import com.yasserakbbach.androidparty.login.data.remote.LoginApi
import com.yasserakbbach.androidparty.login.data.repository.LoginRepositoryImpl
import com.yasserakbbach.androidparty.login.data.repository.SessionRepositoryImpl
import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginApi(
        retrofit: Retrofit,
    ): LoginApi =
        retrofit.create()

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginApi: LoginApi,
    ): LoginRepository =
        LoginRepositoryImpl(loginApi)

    @Provides
    @Singleton
    fun provideSessionRepository(
        @ApplicationContext context: Context,
    ): SessionRepository =
        SessionRepositoryImpl(context)
}