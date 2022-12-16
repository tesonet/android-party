package app.data.api

import login.domain.model.Token
import main.domain.model.Server
import okhttp3.RequestBody
import retrofit2.http.*

interface TesonetService {
    @GET("/v1/servers")
    suspend fun servers(@Header("Authorization") bearer: String): List<Server>

    @Multipart
    @POST("/v1/tokens")
    suspend fun token(@PartMap authorization: Map<String, @JvmSuppressWildcards RequestBody>): Token
}