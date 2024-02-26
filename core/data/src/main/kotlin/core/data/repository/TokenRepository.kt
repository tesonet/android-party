package core.data.repository

import core.data.local.TokenLocalDataSource
import core.data.network.TokenNetworkDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.RequestBody
import javax.inject.Inject

@ActivityRetainedScoped
class TokenRepository @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource,
    private val tokenNetworkDataSource: TokenNetworkDataSource
) {
    suspend fun newToken(authorization: Map<String, RequestBody>) =
        tokenNetworkDataSource.token(authorization)

    suspend fun savedToken() = tokenLocalDataSource.getToken()
    suspend fun saveToken(token: String?) = tokenLocalDataSource.setToken(token)
    fun localTokens() = tokenLocalDataSource.tokens()
}