package com.edvinas.balkaitis.party.login

import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginActivityModule {
    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment
}
