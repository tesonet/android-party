package assignment.tesonet.homework

import android.app.Application
import assignment.tesonet.homework.api.ApiService
import assignment.tesonet.homework.storage.AppPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class App: Application() {
    lateinit var service: ApiService

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)

        service = buildRetrofit()
    }

    private fun buildRetrofit() : ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.serverAddress)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(ApiService::class.java)
        return service
    }
}