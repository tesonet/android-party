package com.k4dima.party.app.ui.di

import com.k4dima.party.app.presentation.di.ViewModelFactoryModule
import com.k4dima.party.login.data.di.LoginRepositoryModule
import com.k4dima.party.login.domain.di.LoginUseCaseModule
import com.k4dima.party.login.presentation.di.LoginViewModelModule
import com.k4dima.party.login.ui.LoginActivity
import com.k4dima.party.login.ui.di.LoginScope
import com.k4dima.party.main.data.di.RepositoryModule
import com.k4dima.party.main.domain.di.UseCaseModule
import com.k4dima.party.main.presentation.di.ViewModelModule
import com.k4dima.party.main.ui.MainActivity
import com.k4dima.party.main.ui.di.MainScope
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