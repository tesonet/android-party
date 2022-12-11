package app.ui.di

import app.presentation.di.ViewModelFactoryModule
import login.domain.di.LoginRepositoryModule
import login.presentation.di.LoginViewModelModule
import login.ui.LoginActivity
import login.ui.di.LoginScope
import main.domain.di.RepositoryModule
import main.presentation.di.ViewModelModule
import main.ui.MainActivity
import main.ui.di.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AppBindingModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [ViewModelFactoryModule::class,
            ViewModelModule::class,
            RepositoryModule::class]
    )
    internal abstract fun mainActivity(): MainActivity

    @LoginScope
    @ContributesAndroidInjector(
        modules = [ViewModelFactoryModule::class,
            LoginViewModelModule::class,
            LoginRepositoryModule::class
        ]
    )
    internal abstract fun loginActivity(): LoginActivity
}