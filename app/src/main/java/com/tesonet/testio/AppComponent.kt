package com.tesonet.testio

import android.app.Application
import com.tesonet.testio.data.local.DatabaseModule
import com.tesonet.testio.data.remote.ApiModule
import com.tesonet.testio.ui.UiModule
import com.tesonet.testio.util.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DatabaseModule::class,
    ApiModule::class,
    UiModule::class,
    UtilModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}