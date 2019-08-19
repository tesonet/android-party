package com.example.androidparty.dagger

import com.example.androidparty.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}