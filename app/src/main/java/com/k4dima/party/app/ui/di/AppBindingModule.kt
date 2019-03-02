package com.k4dima.party.app.ui.di

import com.k4dima.androidparty.features.app.presentation.di.ViewModelFactoryModule
import com.k4dima.androidparty.features.login.data.di.LoginRepositoryModule
import com.k4dima.androidparty.features.login.domain.di.LoginUseCaseModule
import com.k4dima.androidparty.features.login.presentation.di.LoginViewModelModule
import com.k4dima.party.login.ui.LoginActivity
import com.k4dima.party.login.ui.di.LoginScope
import com.k4dima.androidparty.features.main.data.di.RepositoryModule
import com.k4dima.androidparty.features.main.domain.di.UseCaseModule
import com.k4dima.androidparty.features.main.presentation.di.ViewModelModule
import com.k4dima.androidparty.features.main.ui.MainActivity
import com.k4dima.androidparty.features.main.ui.di.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AppBindingModule {
    @MainScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        RepositoryModule::class])
    internal abstract fun mainActivity(): MainActivity

    @LoginScope
    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class,
        LoginViewModelModule::class,
        LoginUseCaseModule::class,
        LoginRepositoryModule::class
    ])
    internal abstract fun loginActivity(): LoginActivity
}