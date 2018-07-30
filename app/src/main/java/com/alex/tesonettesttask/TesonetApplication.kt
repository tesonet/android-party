package com.alex.tesonettesttask

import android.app.Application
import com.alex.tesonettesttask.logic.dagger.DaggerMainComponent
import com.alex.tesonettesttask.logic.dagger.MainComponent
import com.google.gson.Gson
import io.realm.Realm

class TesonetApplication : Application() {

    var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
        mainComponent = DaggerMainComponent.builder().build()
    }

    companion object {
        lateinit var instance: TesonetApplication
        val gson: Gson = Gson()
    }
}