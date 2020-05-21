package com.baruckis.androidparty.ui.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * AppModule will provide app-wide dependencies for a part of the application.
 * It should initialize objects used across our application, such as Room database,
 * Retrofit, Shared Preference, etc.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}