package com.jonastiskus.testio

import android.app.Application
import com.facebook.stetho.Stetho
import com.jonastiskus.testio.network.ApiServiceFactory
import com.jonastiskus.testio.network.service.ServiceProvider

class TestioApplication : Application() {

    private lateinit var serviceProvider : ServiceProvider

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        serviceProvider = ApiServiceFactory(this)
    }

    fun getServiceProvider() : ServiceProvider {
        return serviceProvider
    }
}
