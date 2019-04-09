package com.edvinas.balkaitis.party

import com.edvinas.balkaitis.party.login.fragment.LoginFragment
import com.edvinas.balkaitis.party.login.fragment.LoginModule
import com.edvinas.balkaitis.party.servers.fragment.ServersFragment
import com.edvinas.balkaitis.party.servers.fragment.ServersModule
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ServersModule::class])
    abstract fun contributeServersFragment(): ServersFragment
}
