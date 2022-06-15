package com.czech.androidparty.di

import android.content.Context
import com.czech.androidparty.preferences.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: BaseApplication): SharedPrefs {
        return SharedPrefs(
            context = context
        )
    }
}