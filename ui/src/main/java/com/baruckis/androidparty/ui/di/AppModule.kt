package com.baruckis.androidparty.ui.di

import android.app.Application
import android.content.Context
import com.baruckis.androidparty.local.preferences.PreferenceStorage
import com.baruckis.androidparty.local.preferences.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * AppModule will provide app-wide dependencies for a part of the application.
 * It should initialize objects used across our application, such as Room database,
 * Retrofit, Shared Preference, etc.
 */
@Module
class AppModule {

    @Provides // Annotation informs Dagger compiler that this method is the constructor for the Context return type.
    @Singleton // Annotation informs Dagger compiler that the instance should be created only once in the entire lifecycle of the application.
    fun provideContext(app: Application): Context =
        app // Using provide as a prefix is a common convention but not a requirement.

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): PreferenceStorage =
        SharedPreferenceStorage(
            context
        )

}