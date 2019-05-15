package com.example.ievazygaite.androidparty

import android.app.Application
import com.example.ievazygaite.androidparty.data.DataManager
import com.example.ievazygaite.androidparty.di.component.ApplicationComponent
import com.example.ievazygaite.androidparty.di.component.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        DataManager.initManager(this)
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder().build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: BaseApplication private set
    }
}