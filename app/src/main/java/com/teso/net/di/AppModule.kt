package com.teso.net.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [(StorageModule::class), (NetworkModule::class), (InteractionModule::class)])
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application
}