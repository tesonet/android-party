package lt.marius.testio.api

import android.content.Context
import com.google.gson.Gson
import lt.marius.testio.BuildConfig
import lt.marius.testio.persistance.UserPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by marius on 17.8.21.
 */
class ApiAppServiceFactory : AppServiceFactory {
	override fun createAppService(context: Context): AppService =
			getRetrofit(context).create(AppService::class.java)

	private fun getRetrofit(context: Context): Retrofit {
		val okBuilder = OkHttpClient.Builder()
		if (BuildConfig.DEBUG) {
			val loggingInterceptor = HttpLoggingInterceptor()
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
			okBuilder.interceptors().add(loggingInterceptor)
		}
		okBuilder.interceptors().add(getHeaderInterceptor(context))

		val retrofitBuilder = Retrofit.Builder()
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(Gson()))
		retrofitBuilder.addCallAdapterFactory(RxErrorCallAdapterFactory(RxJava2CallAdapterFactory.create()))
		retrofitBuilder.client(okBuilder.build())
		retrofitBuilder.baseUrl(BuildConfig.API_URL)
		return retrofitBuilder.build()
	}

	private fun getHeaderInterceptor(context: Context): Interceptor? {

		val userPrefs = UserPreferences(context)
		return Interceptor { chain ->
			val requestBuilder = chain.request().newBuilder()
			userPrefs.authorization?.let {
				requestBuilder.header("Authorization", "Bearer $it")
			}

			requestBuilder.header("Content-Type", "application/json")
			chain.proceed(requestBuilder.build())
		}

	}
}