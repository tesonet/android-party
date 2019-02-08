package com.example.justinaszableckisand.androidparty.Servers

import android.content.Context
import com.example.justinaszableckisand.androidparty.API.NetworkingManager
import com.example.justinaszableckisand.androidparty.Models.Server
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServersPresenter(private val mContext: Context, val mView: ServersContract.View) : ServersContract.Presenter {
    init {
        mView.setPresenter(this)
    }

    override fun getServers(token: String) {
        val getServersCall: Call<List<Server>> = NetworkingManager.getApiCalls(mContext)
                .getServers(token)
        getServersCall.enqueue(object : Callback<List<Server>> {
            override fun onFailure(call: Call<List<Server>>, t: Throwable) {
                mView.onError("Check your internet connection and, try again later")
            }

            override fun onResponse(call: Call<List<Server>>, response: Response<List<Server>>) {
                when {
                    response.isSuccessful -> {
                        mView.onSuccess(response.body()!!)
                    }
                    response.code() == 401 -> mView.onError("Check your username or password")
                    response.code() == 404 -> mView.onError("Try again later")
                }
            }

        })
    }
}