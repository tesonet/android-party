package app.ui.di

import android.content.Context
import app.data.api.ApiModule
import app.data.di.PersistenceModule
import app.domain.di.RepositoryModule
import app.ui.Party
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PersistenceModule::class,
        RepositoryModule::class,
        ApiModule::class,
        AppBindingModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<Party> {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}