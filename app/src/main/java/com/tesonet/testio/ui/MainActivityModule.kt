package com.tesonet.testio.ui

import com.tesonet.testio.dagger.FragmentScope
import com.tesonet.testio.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun loginScreen(): LoginFragment

}