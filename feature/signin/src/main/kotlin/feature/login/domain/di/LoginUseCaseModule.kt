package feature.login.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import feature.login.domain.TestioLoginUseCase
import signin.domain.LoginUseCase

@Module
@InstallIn(ViewModelComponent::class)
interface LoginUseCaseModule {
    @Binds
    fun loginUseCase(testioLoginUseCase: TestioLoginUseCase): LoginUseCase
}