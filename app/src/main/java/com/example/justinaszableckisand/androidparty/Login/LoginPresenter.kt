package com.example.justinaszableckisand.androidparty.Login

import android.content.Context
import android.util.Log
import com.example.justinaszableckisand.androidparty.Models.Token
import com.example.justinaszableckisand.androidparty.Models.UserLogin
import com.example.justinaszableckisand.androidparty.API.NetworkingManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (private val mContext : Context, val mView : LoginContract.View): LoginContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    override fun logIn(username: String, password: String) {
       val getTokenCall : Call<Token> = NetworkingManager.getApiCalls(mContext)
               .getToken(UserLogin(username, password))

        getTokenCall.enqueue(object : Callback<Token>{
            override fun onFailure(call: Call<Token>, t: Throwable) {
                mView.onError("Check your internet connection and, try again later")
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                when {
                    response.isSuccessful -> {
                        Log.e("token", response.body()!!.token)
                        mView.onSuccess(response.body()!!.token.toString())
                    }
                    response.code() == 401 -> mView.onError(401)
                    response.code() == 404 -> mView.onError(404)
                }
            }

        })
    }

}