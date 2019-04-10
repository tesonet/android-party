package com.edvinas.balkaitis.party.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class PartyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
