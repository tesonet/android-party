package lt.petraslabutis.testio.dagger

import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import lt.petraslabutis.testio.api.AppService
import lt.petraslabutis.testio.api.AuthenticationService
import lt.petraslabutis.testio.viewmodels.LoginViewModel
import lt.petraslabutis.testio.viewmodels.NavigationViewModel
import lt.petraslabutis.testio.viewmodels.ServerListViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    internal fun provideLoginViewModel(authenticationService: AuthenticationService, preferences: SecurePreferences) =
        LoginViewModel(authenticationService, preferences)

    @Provides
    @Singleton
    internal fun provideNavigationViewModel() = NavigationViewModel()

    @Provides
    @Singleton
    internal fun provideServerListViewModel(appService: AppService) = ServerListViewModel(appService)

}