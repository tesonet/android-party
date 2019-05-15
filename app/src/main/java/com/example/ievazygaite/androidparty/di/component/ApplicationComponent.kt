package com.example.ievazygaite.androidparty.di.component

import com.example.ievazygaite.androidparty.BaseApplication
import com.example.ievazygaite.androidparty.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApplication)
}