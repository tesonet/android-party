package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    @Background
    fun providesBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Singleton
    @Provides
    @Foreground
    fun providesForegroundScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}