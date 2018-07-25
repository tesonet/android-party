package com.teso.net.data_flow.network

import android.text.TextUtils
import com.google.gson.Gson
import com.teso.net.BuildConfig
import com.teso.net.data_flow.interactions.ITokenInteractor
import com.teso.net.data_flow.network.api_models.TokenAnswer
import okhttp3.*
import timber.log.Timber

class TokenInterceptor(private val loginInteractor: ITokenInteractor) : Interceptor {

    private val TOKEN_REQUEST = "token"

    override fun intercept(chain: Interceptor.Chain?): Response {

        val initialRequest: Request = chain?.request()!!
        val modifiedRequest: Request.Builder = initialRequest.newBuilder()

        if (loginInteractor.hasToken()) {
            modifiedRequest.header("Authorization", String.format("Bearer %s", loginInteractor.getToken()))
                    .build()
        }

        val response: Response = chain.proceed(modifiedRequest.build())

        if (initialRequest.url().encodedPath() == TOKEN_REQUEST) return response

        if (response.code() == 401) {
            synchronized(this) {
                if (!TextUtils.isEmpty(loginInteractor.getUserName())) {
                    try {
                        loginInteractor.clearToken()
                        val tokenRequest = buildAuthRequest()
                        val tokenResponse: Response = chain.proceed(tokenRequest)
                        if (tokenResponse.isSuccessful) {
                            val tokenJson: String = tokenResponse.body()!!.string()
                            val newToken: TokenAnswer = Gson().fromJson(tokenJson, TokenAnswer::class.java)
                            if (!TextUtils.isEmpty(newToken.token)) {
                                loginInteractor.setToken(newToken.token ?: "")
                                return chain.proceed(initialRequest.newBuilder()
                                        .addHeader("Authorization", String.format("Bearer %s", newToken.token))
                                        .build())
                            }
                        }
                    } catch (t: Throwable) {
                        Timber.d(t)
                    }
                }
            }
        }
        return response
    }

    private fun buildAuthRequest(): Request {

        val formBody: RequestBody = FormBody.Builder()
                .add("username", loginInteractor.getUserName())
                .add("password", loginInteractor.getPassword())
                .build()

        return Request.Builder()
                .url(BuildConfig.API_BASE_URL + TOKEN_REQUEST)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .post(formBody)
                .build()
    }
}