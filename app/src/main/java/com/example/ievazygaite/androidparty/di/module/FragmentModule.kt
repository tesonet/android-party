package com.example.ievazygaite.androidparty.di.module

import com.example.ievazygaite.androidparty.ui.list.ServerListContract
import com.example.ievazygaite.androidparty.ui.list.ServerListPresenter
import com.example.ievazygaite.androidparty.ui.login.LoginContract
import com.example.ievazygaite.androidparty.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun providesServerListPresenter(): ServerListContract.Presenter {
        return ServerListPresenter()
    }

    @Provides
    fun provideLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }
}

