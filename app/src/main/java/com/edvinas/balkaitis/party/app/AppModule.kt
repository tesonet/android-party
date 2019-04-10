package com.edvinas.balkaitis.party.app

import android.content.Context
import android.preference.PreferenceManager
import com.edvinas.balkaitis.party.repository.PreferencesTokenStorage
import com.edvinas.balkaitis.party.repository.TokenStorage
import com.edvinas.balkaitis.party.utils.schedulers.Io
import com.edvinas.balkaitis.party.utils.schedulers.Main
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(applicationContext: PartyApplication): Context

    @Module
    companion object {
        @JvmStatic @Provides @Io
        fun provideIoScheduler(): Scheduler = Schedulers.io()

        @JvmStatic @Provides @Main
        fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

        @JvmStatic @Provides
        fun provideTokenStorage(context: Context): TokenStorage {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return PreferencesTokenStorage(sharedPreferences)
        }
    }
}
