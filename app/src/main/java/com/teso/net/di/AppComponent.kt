package com.teso.net.di

import com.teso.net.ui.vm.LoadingFragmentVM
import com.teso.net.ui.vm.LoginFragmentVM
import com.teso.net.ui.vm.MainActivityVM
import com.teso.net.ui.vm.ServerListVM
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(siteFragmentVM: ServerListVM)

    fun inject(loginFragmentVM: LoginFragmentVM)

    fun inject(loadingFragmentVM: LoadingFragmentVM)

    fun inject(mainActivityVM: MainActivityVM)
}