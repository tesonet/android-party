package com.example.android_party.injection

import com.example.android_party.ui.login.LoginViewModel
import com.example.android_party.ui.servers.ServersViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for view models.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (RepositoryModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified LoginViewModel.
     * @param loginViewModel LoginViewModel in which to inject the dependencies
     */
    fun inject(loginViewModel: LoginViewModel)
    /**
     * Injects required dependencies into the specified ServersViewModel.
     * @param serversViewModel LoginViewModel in which to inject the dependencies
     */
    fun inject(serversViewModel: ServersViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule) : Builder
    }
}