package com.k4dima.androidparty.features.login.presentation.di

import androidx.lifecycle.ViewModel
import com.k4dima.androidparty.features.app.presentation.di.ViewModelKey
import com.k4dima.androidparty.features.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindMainViewModel(repoViewModel: LoginViewModel): ViewModel
}