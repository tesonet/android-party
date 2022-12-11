package login.domain

import app.domain.DataRepository
import app.data.api.TesonetService
import login.domain.model.Token
import login.ui.di.LoginScope
import okhttp3.RequestBody
import javax.inject.Inject

@LoginScope
class TokenRepository
@Inject
constructor(private val tesonetService: TesonetService) :
    DataRepository<Map<String, RequestBody>, Token> {
    override suspend fun data(parameter: Map<String, RequestBody>) = tesonetService.token(parameter)
}