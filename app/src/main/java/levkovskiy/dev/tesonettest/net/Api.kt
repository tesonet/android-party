package levkovskiy.dev.tesonettest.net

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

  @POST("tokens")
  @FormUrlEncoded
  fun login(
    @Field("username") username: String,
    @Field("password") password: String
  ): Observable<Model.Login>

  @GET("servers")
  fun servers(): Observable<List<Model.Server>>

  companion object {
    fun create(): Api {
      val retrofit = makeRetrofit()
      return retrofit.create(Api::class.java)
    }

    fun create(token: String): Api {
      val retrofit = makeRetrofit(accessTokenProvidingInterceptor(token))
      return retrofit.create(Api::class.java)
    }

  }

}