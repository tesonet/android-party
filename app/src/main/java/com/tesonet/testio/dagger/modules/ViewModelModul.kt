package com.tesonet.testio.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tesonet.testio.dagger.ViewModelFactory
import com.tesonet.testio.dagger.ViewModelKey
import com.tesonet.testio.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideLoinViewModel(): LoginViewModel {
            return LoginViewModel()
        }
    }
}