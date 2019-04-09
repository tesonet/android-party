package com.edvinas.balkaitis.party.login.fragment

import com.edvinas.balkaitis.party.login.mvp.LoginContract
import com.edvinas.balkaitis.party.login.mvp.LoginPresenter
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.ServersService
import com.edvinas.balkaitis.party.utils.schedulers.Main
import com.edvinas.balkaitis.party.utils.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
abstract class LoginModule {

    @Module
    companion object {
        @FragmentScope @JvmStatic @Provides
        fun providePresenter(
            loginService: LoginService,
            tokenStorage: TokenStorage,
            serversService: ServersService,
            @Main mainScheduler: Scheduler
        ): LoginContract.Presenter {
            return LoginPresenter(mainScheduler, loginService, tokenStorage, serversService)
        }
    }
}
