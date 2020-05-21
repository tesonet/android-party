package com.baruckis.androidparty.ui.di

import android.app.Application
import com.baruckis.androidparty.ui.AndroidPartyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Singleton component interface for the app. It ties all the modules together.
 * The component is used to connect objects to their dependencies.
 * Dagger will auto-generate DaggerAppComponent which is used for initialization at Application.
 */
@Singleton
@Component(
    modules = [
        // AndroidInjectionModule is a class of Dagger and we don't need to create it.
        // If you want to use injection in fragment then you should use AndroidSupportInjectionModule.class else
        // use AndroidInjectionModule.
        AndroidInjectionModule::class,
        AppModule::class,
        DomainModule::class,
        DataModule::class,
        LocalModule::class,
        RemoteModule::class,
        PresentationModule::class,
        UiModule::class
    ]
)
interface AppComponent : AndroidInjector<AndroidPartyApp> {

    @Component.Builder // Used for instantiation of a component.
    interface Builder {

        @BindsInstance // Bind our application instance to our Dagger graph.
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    // The application which is allowed to request the dependencies declared by the modules.
    override fun inject(app: AndroidPartyApp)

}