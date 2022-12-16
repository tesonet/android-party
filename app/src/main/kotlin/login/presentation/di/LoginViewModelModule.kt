package login.presentation.di

import androidx.lifecycle.ViewModel
import app.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap
import login.presentation.LoginViewModel

@InstallIn(ActivityRetainedComponent::class)
@Module
interface LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindMainViewModel(repoViewModel: LoginViewModel): ViewModel
}