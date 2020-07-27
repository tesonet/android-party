package lt.havefun.tesonetfunparty.network

import io.reactivex.Single
import lt.havefun.tesonetfunparty.data.Server
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("tokens")
    fun generateToken(@Body request: TokenRequest): Single<TokenResponse>

    @GET("servers")
    fun getServersList(): Single<List<Server>?>
}