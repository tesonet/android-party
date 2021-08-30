package com.tesonet.testio.dagger.components

import com.tesonet.testio.MainApplication
import com.tesonet.testio.dagger.ApplicationSingleton
import com.tesonet.testio.dagger.modules.ActivityModule
import com.tesonet.testio.dagger.modules.ApplicationModule
import com.tesonet.testio.dagger.modules.ManagersModule
import com.tesonet.testio.dagger.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@ApplicationSingleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ManagersModule::class
    ],
    dependencies = [ServiceComponent::class]
)

interface ApplicationComponent {
    fun inject(application: MainApplication)
}