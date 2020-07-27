package lt.havefun.tesonetfunparty

import io.reactivex.Single
import lt.havefun.tesonetfunparty.data.Server
import lt.havefun.tesonetfunparty.network.Api
import lt.havefun.tesonetfunparty.network.TokenRequest
import lt.havefun.tesonetfunparty.network.TokenResponse
import java.lang.Exception

class MockedFailedRequestsApi: Api {
    override fun generateToken(request: TokenRequest): Single<TokenResponse> {
        return Single.error(Exception("testerror"))
    }

    override fun getServersList(): Single<List<Server>?> {
        return Single.fromCallable {
            null
        }
    }
}