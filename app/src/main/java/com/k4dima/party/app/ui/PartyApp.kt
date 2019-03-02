package com.k4dima.party.app.ui

import com.k4dima.party.app.ui.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class PartyApp : DaggerApplication() {
    override fun applicationInjector() = DaggerApplicationComponent.builder().context(this).build()
}