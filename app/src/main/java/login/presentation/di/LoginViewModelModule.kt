package login.presentation.di

import androidx.lifecycle.ViewModel
import app.presentation.di.ViewModelKey
import login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindMainViewModel(repoViewModel: LoginViewModel): ViewModel
}