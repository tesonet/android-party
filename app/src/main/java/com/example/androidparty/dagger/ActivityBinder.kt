package com.example.androidparty.dagger

import com.example.androidparty.view.LoginFragment
import com.example.androidparty.view.MainActivity
import com.example.androidparty.view.ServerListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindLoginFragment(): LoginFragment
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindServerListFragment(): ServerListFragment
}