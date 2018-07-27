package com.playground.ugnius.homework.global

import android.app.Application
import com.playground.ugnius.homework.di.components.DaggerMainComponent
import com.playground.ugnius.homework.di.components.MainComponent
import io.realm.Realm

class App : Application() {

    var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        mainComponent = DaggerMainComponent.builder().build()
    }
}