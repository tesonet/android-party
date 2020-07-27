package lt.havefun.tesonetfunparty.injections.modules

import dagger.Module
import dagger.Provides
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.data.LoginRepository
import lt.havefun.tesonetfunparty.network.Api
import lt.havefun.tesonetfunparty.ui.login.LoginViewModel
import javax.inject.Singleton

@Module
class LoginModule {

    @Provides
    @Singleton
    fun providesLoginViewModelFactory(
        loginRepository: LoginRepository,
        preferencesManager: IPreferencesManager
    ): LoginViewModel.Factory {
        return LoginViewModel.Factory(
            loginRepository = loginRepository,
            preferencesManager = preferencesManager
        )
    }

    @Provides
    @Singleton
    fun providesLoginRepository(api: Api): LoginRepository {
        return LoginRepository(api)
    }
}