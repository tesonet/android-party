package com.edvinas.balkaitis.party.login

import com.edvinas.balkaitis.party.login.fragment.LoginFragment
import com.edvinas.balkaitis.party.login.fragment.LoginModule
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginFragment(): LoginFragment
}
