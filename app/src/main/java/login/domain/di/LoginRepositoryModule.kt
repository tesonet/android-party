package login.domain.di

import app.domain.DataRepository
import login.domain.TokenRepository
import login.domain.model.Token
import login.ui.di.LoginScope
import dagger.Module
import dagger.Provides
import okhttp3.RequestBody

@Module
object LoginRepositoryModule {
    @JvmStatic
    @LoginScope
    @Provides
    fun provideServersRepository(tokenRepository: TokenRepository):
            DataRepository<Map<String, RequestBody>, Token> = tokenRepository
}