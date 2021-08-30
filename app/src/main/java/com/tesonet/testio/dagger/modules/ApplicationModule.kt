package com.tesonet.testio.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    open fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    open fun provideApplication(): Application {
        return application
    }
}