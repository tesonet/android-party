package com.ne2rnas.party.injection.component

import com.ne2rnas.party.base.BaseView
import com.ne2rnas.party.injection.module.ContextModule
import com.ne2rnas.party.injection.module.NetworkModule
import com.ne2rnas.party.injection.module.SharedPrefsModule
import com.ne2rnas.party.ui.login.LoginPresenter
import com.ne2rnas.party.ui.main.MainPresenter
import com.ne2rnas.party.ui.servers.ServersPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (SharedPrefsModule::class)])
interface PresenterInjector {

    fun inject(mainPresenter: MainPresenter)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(serversPresenter: ServersPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun contextModule(contextModule: ContextModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun sharedPreferencesModule(sharedPreferencesModule: SharedPrefsModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}
