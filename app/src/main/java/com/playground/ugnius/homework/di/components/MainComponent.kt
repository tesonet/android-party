package com.playground.ugnius.homework.di.components

import com.playground.ugnius.homework.views.LoginFragment
import com.playground.ugnius.homework.di.modules.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface MainComponent {
    fun inject(loginFragment: LoginFragment)
}