package com.edvinas.balkaitis.party.servers.fragment

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.list.ServersAdapter
import com.edvinas.balkaitis.party.servers.list.ServersViewHolderFactory
import com.edvinas.balkaitis.party.servers.mvp.ServersContract
import com.edvinas.balkaitis.party.servers.mvp.ServersPresenter
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
abstract class ServersModule {
    @Module
    companion object {
        @FragmentScope @JvmStatic @Provides
        fun providePresenter(tokenStorage: TokenStorage): ServersContract.Presenter {
            return ServersPresenter(tokenStorage)
        }

        @JvmStatic @Provides
        fun provideServersAdapter(): ServersAdapter = ServersAdapter(ServersViewHolderFactory())
    }
}
