package com.demo.androidparty.di.module

import com.demo.androidparty.di.annotations.IO
import com.demo.androidparty.di.annotations.Main
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatcherModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @IO
        internal fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @JvmStatic
        @Main
        internal fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }
}