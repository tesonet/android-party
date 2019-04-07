package com.edvinas.balkaitis.party.app

import com.edvinas.balkaitis.party.utils.schedulers.Io
import com.edvinas.balkaitis.party.utils.schedulers.Main
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class AppModule {
    @Module
    companion object {
        @JvmStatic @Provides @Io
        fun provideIoScheduler(): Scheduler = Schedulers.io()

        @JvmStatic @Provides @Main
        fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
    }
}
