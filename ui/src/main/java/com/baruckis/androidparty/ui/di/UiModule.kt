package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

}