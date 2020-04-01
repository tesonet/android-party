package com.assignment.android_party2.servers.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.assignment.android_party2.api.Api
import com.assignment.android_party2.servers.models.ServerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.assignment.android_party2.TestioApplication
import com.assignment.android_party2.utils.Coroutines


class ServersRepository {

    val TAG = ServersRepository::class.java.simpleName

    fun getServersFromDb(): LiveData<List<ServerModel>> {
        return TestioApplication.database!!.serversDao().getServers()
    }

    fun getServersFromApiAndUpdateDb(token: String) {

        Api().getServers(token).enqueue(object : Callback<List<ServerModel>> {

            override fun onResponse(
                call: Call<List<ServerModel>>,
                response: Response<List<ServerModel>>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    Coroutines.io {
                        TestioApplication.database!!.serversDao().deleteServers()
                        TestioApplication.database!!.serversDao().insertServers(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<List<ServerModel>>, t: Throwable) {
                Log.e(TAG, "Getting servers failed, check the logs.")
                t.printStackTrace()
            }

        })
    }


}