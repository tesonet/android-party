package com.edvinas.balkaitis.party.app

import com.edvinas.balkaitis.party.MainActivity
import com.edvinas.balkaitis.party.login.LoginActivityModule
import com.edvinas.balkaitis.party.utils.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppContributorsModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun contributePostsActivity(): MainActivity
}
