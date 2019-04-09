package com.edvinas.balkaitis.party.servers.fragment

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.list.ServersAdapter
import com.edvinas.balkaitis.party.servers.list.ServersViewHolderFactory
import com.edvinas.balkaitis.party.servers.mvp.ServersContract
import com.edvinas.balkaitis.party.servers.mvp.ServersPresenter
import com.edvinas.balkaitis.party.servers.network.ServersService
import com.edvinas.balkaitis.party.utils.schedulers.Main
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
abstract class ServersModule {
    @Module
    companion object {
        @FragmentScope @JvmStatic @Provides
        fun providePresenter(
                tokenStorage: TokenStorage,
                @Main mainScheduler: Scheduler,
                serversService: ServersService
        ): ServersContract.Presenter {
            return ServersPresenter(mainScheduler, tokenStorage, serversService)
        }

        @JvmStatic @Provides
        fun provideServersAdapter(): ServersAdapter = ServersAdapter(ServersViewHolderFactory())
    }
}
