package lt.havefun.tesonetfunparty.data

import io.reactivex.Single
import lt.havefun.tesonetfunparty.network.Api
import lt.havefun.tesonetfunparty.network.TokenRequest
import lt.havefun.tesonetfunparty.network.TokenResponse

data class LoginRepository(private val api: Api) {
    fun getToken(username: String, password: String): Single<TokenResponse> {
        return api.generateToken(TokenRequest(username, password))
    }
}