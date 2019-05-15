package com.example.ievazygaite.androidparty.di.module

import android.app.Application
import android.content.Context
import com.example.ievazygaite.androidparty.BaseApplication
import com.example.ievazygaite.androidparty.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApplication) {
    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    @PerApplication
    fun provideApplicationContext(): Context {
        return this.baseApp
    }

}