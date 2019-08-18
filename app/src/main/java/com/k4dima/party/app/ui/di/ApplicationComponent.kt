package com.k4dima.party.app.ui.di

import android.content.Context
import com.k4dima.party.app.data.api.ApiModule
import com.k4dima.party.app.data.di.PersistenceModule
import com.k4dima.party.app.data.di.RepositoryModule
import com.k4dima.party.app.ui.Party
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [PersistenceModule::class,
    RepositoryModule::class,
    ApiModule::class,
    AppBindingModule::class,
    AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<Party> {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun context(context: Context): Builder
    }
}