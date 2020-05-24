package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.ui.launcher.LauncherActivity
import com.baruckis.androidparty.ui.login.LoadingFragment
import com.baruckis.androidparty.ui.login.LoginActivity
import com.baruckis.androidparty.ui.login.LoginFragment
import com.baruckis.androidparty.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributeLauncherActivity(): LauncherActivity

    @ContributesAndroidInjector // Attaches activity to Dagger graph.
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector() // Attaches fragment to Dagger graph.
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoadingFragment(): LoadingFragment

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}