package com.edvinas.balkaitis.party.servers.fragment

import com.edvinas.balkaitis.party.servers.mvp.ServersPresenter
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
abstract class ServersModule {
    @Module
    companion object {
        @FragmentScope @JvmStatic @Provides
        fun providePresenter() = ServersPresenter()
    }
}
