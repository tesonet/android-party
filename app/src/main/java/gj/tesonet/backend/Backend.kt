package gj.tesonet.backend

import androidx.annotation.VisibleForTesting
import gj.tesonet.BuildConfig
import gj.tesonet.data.model.Server
import gj.tesonet.data.model.Token
import gj.tesonet.data.model.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.logging.HttpLoggingInterceptor

interface Backend {

    @GET("servers")
    suspend fun getServers(@Header("Authorization") token: String): List<Server>

    @POST("tokens")
    suspend fun login(@Body user: User): Token

    companion object {

        // fast way to mock network server
        private var _url: String? = null

        var url: String
            @VisibleForTesting
            set(value) {
                _url = value
            }
            get() = _url ?: "https://playground.tesonet.lt/v1/"


        fun create(): Backend {
            val builder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(Backend::class.java)
        }

    }

}
