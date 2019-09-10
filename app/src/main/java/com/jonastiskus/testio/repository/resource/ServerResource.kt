package com.jonastiskus.testio.repository.resource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.jonastiskus.testio.repository.room.entity.ServerEntity
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.network.service.ServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerResource(private val serviceProvider: ServiceProvider,  private val token : String) {

    private var call : Call<List<ServerGsonModel>> = serviceProvider.getApiService().getServers(token)

    private val listMutableLiveData = MutableLiveData<List<ServerGsonModel>>()
    val listLiveData: LiveData<List<ServerGsonModel>> = listMutableLiveData

    private val exceptionMutableLiveData = MutableLiveData<Throwable>()
    val exception: LiveData<Throwable> = exceptionMutableLiveData

    val cachedListLiveData = serviceProvider.getAppDatabase().serverDao().getAll()

    fun load() {
        serviceProvider.getAuthService().makeRequest(call, object : Callback<List<ServerGsonModel>> {
            override fun onResponse(call: Call<List<ServerGsonModel>>, response: Response<List<ServerGsonModel>>) {
                listMutableLiveData.value = response.body()
                insertToDatabase(response.body())
            }

            override fun onFailure(call: Call<List<ServerGsonModel>>, t: Throwable) {
                exceptionMutableLiveData.value = t
            }
        })
    }

    private fun insertToDatabase(dataList: List<ServerGsonModel>?) {
        val gson = Gson()
        val json = gson.toJson(dataList)
        val serverEntity = ServerEntity(json)

        Thread(Runnable {
            serviceProvider.getAppDatabase().serverDao().insert(serverEntity)
        }).start()
    }


}