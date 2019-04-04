package com.edvinas.balkaitis.party.app

import com.edvinas.balkaitis.party.login.LoginActivity
import com.edvinas.balkaitis.party.utils.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppContributorsModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributePostsActivity(): LoginActivity
}
