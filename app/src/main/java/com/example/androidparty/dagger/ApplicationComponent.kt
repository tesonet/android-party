package com.example.androidparty.dagger

import com.example.androidparty.AndroidPartyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityBinder::class])
interface ApplicationComponent : AndroidInjector<AndroidPartyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AndroidPartyApplication): Builder

        fun build(): ApplicationComponent
    }
    override fun inject(app: AndroidPartyApplication)
}