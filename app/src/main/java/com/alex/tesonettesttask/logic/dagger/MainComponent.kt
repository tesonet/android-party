package com.alex.tesonettesttask.logic.dagger

import com.alex.tesonettesttask.ui.activities.MainActivity
import com.alex.tesonettesttask.ui.fragments.LoginFragment
import com.alex.tesonettesttask.ui.fragments.ServersFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, CacheModule::class, PresenterModule::class])
interface MainComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(serversFragment: ServersFragment)
    fun inject(mainActivity: MainActivity)
}