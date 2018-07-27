package com.playground.ugnius.homework.di.components

import com.playground.ugnius.homework.views.fragments.LoginFragment
import com.playground.ugnius.homework.di.modules.NetworkModule
import com.playground.ugnius.homework.di.modules.UtilitiesModule
import com.playground.ugnius.homework.views.activites.MainActivity
import com.playground.ugnius.homework.views.fragments.ServersFragment
import dagger.Component

@Component(modules = [NetworkModule::class, UtilitiesModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(loginFragment: ServersFragment)
}