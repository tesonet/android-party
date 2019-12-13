package com.giedrius.androidparty.task.dependency

import com.giedrius.androidparty.task.view.LoginFragment
import com.giedrius.androidparty.task.view.MainActivity
import com.giedrius.androidparty.task.view.ServersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector(modules = [ApplicationViewModelsModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ApplicationViewModelsModule::class])
    internal abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [ApplicationViewModelsModule::class])
    internal abstract fun bindServersFragment(): ServersFragment
}