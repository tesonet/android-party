package com.giedrius.androidparty.task.dependency

import com.giedrius.androidparty.task.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityBinder::class])
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder interface Builder {
        @BindsInstance fun application(application: App): Builder
        fun build(): ApplicationComponent
    }
    override fun inject(app: App)
}