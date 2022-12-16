package login.domain

import app.data.api.TesonetService
import app.domain.DataRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import login.domain.model.Token
import okhttp3.RequestBody
import javax.inject.Inject

@ActivityRetainedScoped
class TokenRepository
@Inject
constructor(private val tesonetService: TesonetService) :
    DataRepository<Map<String, RequestBody>, Token> {
    override suspend fun data(parameter: Map<String, RequestBody>) = tesonetService.token(parameter)
}