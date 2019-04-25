package net.justinas.tesonetapp.withlib

import android.app.Application
import net.justinas.tesonetapp.withlib.di.appModule
import net.justinas.tesonetapp.withlib.di.interaqctorModule
import net.justinas.tesonetapp.withlib.di.repositoryModule
import net.justinas.tesonetapp.withlib.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application () {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, repositoryModule, viewModelModule, interaqctorModule))
    }
}

