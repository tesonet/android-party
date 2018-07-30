package com.alex.tesonettesttask.logic.dagger

import com.alex.tesonettesttask.logic.model.LoginModel
import com.alex.tesonettesttask.logic.model.ServersModel
import com.alex.tesonettesttask.ui.presenters.LoginPresenter
import com.alex.tesonettesttask.ui.presenters.ServerPresenter
import dagger.Module
import dagger.Provides


@Module
class PresenterModule {
    @Provides
    fun provideLoginPresenter(loginModel: LoginModel, serversModel: ServersModel): LoginPresenter {
        return LoginPresenter(loginModel, serversModel)
    }

    @Provides
    fun provideServerPresenter(loginModel: LoginModel, serversModel: ServersModel): ServerPresenter {
        return ServerPresenter(loginModel, serversModel)
    }
}