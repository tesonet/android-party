package login.domain.di

import app.domain.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import login.domain.TokenRepository
import login.domain.model.Token
import okhttp3.RequestBody

@InstallIn(ActivityRetainedComponent::class)
@Module
object LoginRepositoryModule {
    @ActivityRetainedScoped
    @Provides
    fun provideServersRepository(tokenRepository: TokenRepository):
            DataRepository<Map<String, RequestBody>, Token> = tokenRepository
}