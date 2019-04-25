package net.justinas.tesonetapp.withlib.domain.remote

import io.reactivex.Single
import retrofit2.http.*

interface TesonetApi {

    companion object {
        const val TOKEN_URL = "/v1/tokens"
        const val LIST_URL = "/v1/servers"
    }

    @POST(TOKEN_URL)
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String): Single<Token>

    @GET(LIST_URL)
    fun getList(): Single<List<Server>>
}