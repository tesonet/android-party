package com.k4dima.party.app.ui

import android.os.StrictMode
import com.k4dima.party.BuildConfig.DEBUG
import com.k4dima.party.app.ui.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class Party : DaggerApplication() {
    override fun applicationInjector() = DaggerApplicationComponent.builder().context(this).build()

    init {
        if (DEBUG) StrictMode.enableDefaults()
    }
}