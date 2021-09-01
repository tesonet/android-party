package com.tesonet.testio.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tesonet.testio.dagger.ViewModelFactory
import com.tesonet.testio.dagger.ViewModelKey
import com.tesonet.testio.managers.ServersManager
import com.tesonet.testio.ui.loading.LoadingViewModel
import com.tesonet.testio.ui.login.LoginViewModel
import com.tesonet.testio.ui.servers.ServersViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(LoadingViewModel::class)
    abstract fun bindLoadingViewModel(loadingViewModel: LoadingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ServersViewModel::class)
    abstract fun bindServersViewModel(serversViewModel: ServersViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideLoinViewModel(serversManager: ServersManager): LoginViewModel {
            return LoginViewModel(serversManager)
        }

        @Provides
        @JvmStatic
        fun provideLoadingViewModel(serversManager: ServersManager): LoadingViewModel {
            return LoadingViewModel(serversManager)
        }

        @Provides
        @JvmStatic
        fun provideServersViewModel(serversManager: ServersManager): ServersViewModel {
            return ServersViewModel(serversManager)
        }
    }
}