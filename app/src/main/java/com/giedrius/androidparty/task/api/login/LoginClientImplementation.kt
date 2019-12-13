package com.giedrius.androidparty.task.api.login

import android.util.Log
import com.giedrius.androidparty.task.viewmodel.Token
import com.giedrius.androidparty.task.viewmodel.LoginBody
import com.giedrius.androidparty.task.api.ApiClient
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class LoginClientImplementation : LoginClient {
    private var retrofit: Retrofit
    private var mApiClient: ApiClient

    init {
        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiClient = retrofit.create(ApiClient::class.java)
    }

    override fun login(body: LoginBody, apiListener: ApiListener<Token>) {
        val call: Call<Token> = mApiClient.getToken(body)
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                apiListener.onResult(response.body())
            }

            override fun onFailure(call: Call<Token>, error: Throwable) {
                error.message.let {
                    Log.e("Login call failed", it)
                }
            }
        })
    }
}

