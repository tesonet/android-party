package com.edvinas.balkaitis.party.app

import com.edvinas.balkaitis.party.utils.network.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppContributorsModule::class,
        NetworkModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<PartyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PartyApplication>()
}
