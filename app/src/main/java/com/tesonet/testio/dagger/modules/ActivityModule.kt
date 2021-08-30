package com.tesonet.testio.dagger.modules

import com.tesonet.testio.dagger.ActivityScope
import com.tesonet.testio.ui.MainActivity
import com.tesonet.testio.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}