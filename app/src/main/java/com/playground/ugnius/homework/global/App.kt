package com.playground.ugnius.homework.global

import android.app.Application
import com.playground.ugnius.homework.di.components.DaggerMainComponent
import com.playground.ugnius.homework.di.components.MainComponent
import com.playground.ugnius.homework.di.modules.UtilitiesModule

class App : Application() {

    var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .utilitiesModule(UtilitiesModule(this))
            .build()
    }
}