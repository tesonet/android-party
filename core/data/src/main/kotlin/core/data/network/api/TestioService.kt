package core.data.network.api

import core.data.model.Token
import core.data.network.api.model.NetworkServer
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap

interface TestioService {
    @GET("/v1/servers")
    suspend fun servers(@Header("Authorization") token: String): List<NetworkServer>

    @POST("/v1/tokens")
    @Multipart
    suspend fun token(@PartMap authorization: Map<String, @JvmSuppressWildcards RequestBody>): Token
}