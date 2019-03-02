package com.k4dima.androidparty.features.login.data.di

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.login.data.TokenRepository
import com.k4dima.androidparty.features.login.data.model.Token
import com.k4dima.party.login.ui.di.LoginScope
import dagger.Module
import dagger.Provides
import okhttp3.RequestBody

@Module
object LoginRepositoryModule {
    @JvmStatic
    @LoginScope
    @Provides
    fun provideServersRepository(tokenRepository: TokenRepository):
            DataRepository<Map<String, RequestBody>, Token> = tokenRepository
}