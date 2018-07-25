package com.teso.net.di

import com.teso.net.ui.vm.LoginFragmentVM
import com.teso.net.ui.vm.SiteFragmentVM
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(siteFragmentVM: SiteFragmentVM)
    fun inject(loginFragmentVM: LoginFragmentVM)
}