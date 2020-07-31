package com.demo.androidparty.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.demo.androidparty.base.ViewModelFactory
import com.demo.androidparty.di.annotations.DaggerScope
import com.demo.androidparty.di.annotations.ViewModelKey
import com.demo.androidparty.di.module.LoginFragmentModule
import com.demo.androidparty.di.module.ServersListFragmentModule
import com.demo.androidparty.storage.preferences.AppPreferences
import com.demo.androidparty.ui.login.LoginFragment
import com.demo.androidparty.ui.list.ServersListFragment
import com.demo.androidparty.ui.main.MainActivity
import com.demo.androidparty.ui.main.MainModel
import com.demo.androidparty.ui.main.MainViewModel
import com.demo.androidparty.utils.InternetStateProvider
import com.demo.androidparty.utils.InternetStateProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule private constructor() {

    @DaggerScope(LoginFragment::class)
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    internal abstract fun provideLoginFragment(): LoginFragment

    @DaggerScope(ServersListFragment::class)
    @ContributesAndroidInjector(modules = [ServersListFragmentModule::class])
    internal abstract fun provideServersListFragment(): ServersListFragment

    @Binds
    internal abstract fun bindInternetStateProvider(
        provider: InternetStateProviderImpl
    ): InternetStateProvider

    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        internal fun provideMainViewModelFactory(model: MainModel): ViewModel = MainViewModel(model)

        @JvmStatic
        @Provides
        internal fun provideMainViewModel(
            target: MainActivity,
            factory: ViewModelFactory
        ): MainViewModel = ViewModelProviders.of(target, factory).get(MainViewModel::class.java)

        @JvmStatic
        @Provides
        internal fun provideMainModel(prefs: AppPreferences): MainModel = MainModel(prefs)
    }
}