package com.demo.androidparty.di.component

import com.demo.androidparty.AndroidPartyApp
import com.demo.androidparty.di.ActivityBindingModule
import com.demo.androidparty.di.module.ApiModule
import com.demo.androidparty.di.module.AppModule
import com.demo.androidparty.di.module.DispatcherModule
import com.demo.androidparty.di.module.StorageModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        DispatcherModule::class,
        ApiModule::class,
        StorageModule::class
    ]
)
interface AppComponent : AndroidInjector<AndroidPartyApp> {

    /**
     * AppComponent Builder interface. All implementation part is handled by a dagger compiler.
     */
    @Component.Factory
    interface Factory : AndroidInjector.Factory<AndroidPartyApp>
}