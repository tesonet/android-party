package com.ac.androidparty.login.data.di

import com.ac.androidparty.core.network.NetworkConstants
import com.ac.androidparty.core.network.RetrofitConstants
import com.ac.androidparty.login.data.remote.LoginApi
import com.ac.androidparty.login.data.repository.LoginRepository
import com.ac.androidparty.login.data.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    internal fun providesLoginApi(
        @Named(RetrofitConstants.BASE_RETROFIT) retrofit: Retrofit,
    ): LoginApi =
        retrofit.newBuilder().build().create()

    @Provides
    internal fun provideLoginRepository(
        repository: LoginRepositoryImpl
    ): LoginRepository = repository
}