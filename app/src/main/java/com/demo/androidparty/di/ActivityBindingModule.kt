package com.demo.androidparty.di

import com.demo.androidparty.ui.main.MainActivity
import com.demo.androidparty.di.annotations.DaggerScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @DaggerScope(MainActivity::class)
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun provideMainActivity(): MainActivity
}