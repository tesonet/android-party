package com.demo.androidparty.di.module

import android.content.Context
import com.demo.androidparty.AndroidPartyApp
import com.demo.androidparty.di.annotations.AppContext
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule private constructor() {

    @Module
    companion object {
        @Provides
        @AppContext
        @JvmStatic
        internal fun provideApplicationContext(instance: AndroidPartyApp): Context =
            instance.applicationContext
    }
}