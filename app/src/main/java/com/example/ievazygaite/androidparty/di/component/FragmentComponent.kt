package com.example.ievazygaite.androidparty.di.component

import com.example.ievazygaite.androidparty.di.module.FragmentModule
import com.example.ievazygaite.androidparty.ui.list.ServerListFragment
import com.example.ievazygaite.androidparty.ui.login.LoginFragment
import dagger.Component


@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(serverListFragment: ServerListFragment)
    fun inject(loginFragment: LoginFragment)
}