package com.tesonet.testio

import android.app.Application
import com.tesonet.testio.dagger.components.ApplicationComponent
import com.tesonet.testio.dagger.components.DaggerApplicationComponent
import com.tesonet.testio.dagger.components.DaggerServiceComponent
import com.tesonet.testio.dagger.components.ServiceComponent
import com.tesonet.testio.dagger.modules.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var serviceComponent: ServiceComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .serviceComponent(provideServiceComponent())
            .build()
            .also { applicationComponent ->
                applicationComponent.inject(this)
            }
    }

    private fun provideServiceComponent(): ServiceComponent {
        if (!this::serviceComponent.isInitialized) {
            serviceComponent = DaggerServiceComponent
                .builder()
                .build()
        }
        return serviceComponent
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}