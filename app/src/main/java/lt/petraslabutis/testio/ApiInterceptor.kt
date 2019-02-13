package lt.petraslabutis.testio

import com.securepreferences.SecurePreferences
import lt.petraslabutis.testio.viewmodels.LoginViewModel.Companion.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInterceptor @Inject constructor(private val preferences: SecurePreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().newBuilder().apply {
            preferences.getString(AUTH_TOKEN, "")?.let {
                if (it.isNotEmpty()) {
                    this.addHeader("Authorization", "Bearer $it")
                }
            }
        }.build())
}