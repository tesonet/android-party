package com.example.ievazygaite.androidparty.di.component

import com.example.ievazygaite.androidparty.di.module.ActivityModule
import com.example.ievazygaite.androidparty.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}