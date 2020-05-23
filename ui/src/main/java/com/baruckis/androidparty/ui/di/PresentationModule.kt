package com.baruckis.androidparty.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.launcher.LauncherViewModel
import com.baruckis.androidparty.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Will be responsible for providing ViewModels.
 */
@Module
abstract class PresentationModule {

    // We'd like to take this implementation of the ViewModel class and make it available
    // in an injectable map with LoginViewModel::class as a key to that map.
    @Binds
    @IntoMap
    // We use a restriction on multibound map defined with @ViewModelKey annotation,
    // and if don't need any, we should use @ClassKey annotation provided by Dagger.
    @ViewModelKey(LauncherViewModel::class)
    abstract fun bindLauncherViewModel(launcherViewModel: LauncherViewModel): ViewModel

    // We'd like to take this implementation of the ViewModel class and make it available
    // in an injectable map with LoginViewModel::class as a key to that map.
    @Binds
    @IntoMap
    // We use a restriction on multibound map defined with @ViewModelKey annotation,
    // and if don't need any, we should use @ClassKey annotation provided by Dagger.
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}