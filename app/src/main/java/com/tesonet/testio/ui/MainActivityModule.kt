package com.tesonet.testio.ui

import com.tesonet.testio.dagger.FragmentScope
import com.tesonet.testio.ui.loading.LoadingFragment
import com.tesonet.testio.ui.login.LoginFragment
import com.tesonet.testio.ui.servers.ServersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun loginScreen(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun loadingScreen(): LoadingFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun serversFragment(): ServersFragment
}