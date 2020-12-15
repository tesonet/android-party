package com.example.android_party

import androidx.lifecycle.ViewModel
import com.example.android_party.injection.DaggerViewModelInjector
import com.example.android_party.injection.NetworkModule
import com.example.android_party.injection.RepositoryModule
import com.example.android_party.injection.ViewModelInjector
import com.example.android_party.ui.login.LoginViewModel
import com.example.android_party.ui.servers.ServersViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
            is ServersViewModel -> injector.inject(this)
        }
    }}