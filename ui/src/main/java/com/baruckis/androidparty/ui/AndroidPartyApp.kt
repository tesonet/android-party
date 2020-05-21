package com.baruckis.androidparty.ui

import com.baruckis.androidparty.ui.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AndroidPartyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // Here we initialize Dagger. DaggerAppComponent is auto-generated from AppComponent.
        return DaggerAppComponent.builder().application(this).build()
    }

}