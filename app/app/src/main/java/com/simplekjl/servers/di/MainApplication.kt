package com.simplekjl.servers.di

import android.app.Application
import com.simplekjl.data.di.dataModule
import com.simplekjl.domain.di.domainModule
import com.simplekjl.servers.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.ERROR
import org.koin.core.logger.Level.NONE

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //start koin
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) ERROR else NONE)
            androidContext(this@MainApplication)
            modules(listOf(mainModule, dataModule, domainModule))
        }
    }
}