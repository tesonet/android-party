package lt.havefun.tesonetfunparty

import io.reactivex.Single
import lt.havefun.tesonetfunparty.data.Server
import lt.havefun.tesonetfunparty.network.Api
import lt.havefun.tesonetfunparty.network.TokenRequest
import lt.havefun.tesonetfunparty.network.TokenResponse
import java.lang.Exception

class MockedApi: Api {
    override fun generateToken(request: TokenRequest): Single<TokenResponse> {
        if (request.username == "test" && request.password == "test") {
            return Single.fromCallable {
                TokenResponse("testToken")
            }
        } else {
            return Single.error(Exception("testerror"))
        }
    }

    override fun getServersList(): Single<List<Server>?> {
        return Single.fromCallable {
            val server = Server("test", 123)
            val serverList: MutableList<Server> = ArrayList()
            serverList.add(server)
            serverList
        }
    }
}