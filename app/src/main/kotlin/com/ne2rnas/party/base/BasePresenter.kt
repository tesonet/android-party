package com.ne2rnas.party.base

import com.ne2rnas.party.injection.component.DaggerPresenterInjector
import com.ne2rnas.party.injection.component.PresenterInjector
import com.ne2rnas.party.injection.module.ContextModule
import com.ne2rnas.party.injection.module.NetworkModule
import com.ne2rnas.party.ui.login.LoginPresenter
import com.ne2rnas.party.ui.main.MainPresenter
import com.ne2rnas.party.ui.servers.ServersPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}

    private fun inject() {
        when (this) {
            is MainPresenter -> injector.inject(this)
            is LoginPresenter -> injector.inject(this)
            is ServersPresenter -> injector.inject(this)
        }
    }
}
