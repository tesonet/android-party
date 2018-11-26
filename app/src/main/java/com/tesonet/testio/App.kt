package com.tesonet.testio

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}