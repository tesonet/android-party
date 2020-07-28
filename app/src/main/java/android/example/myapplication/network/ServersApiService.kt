package android.example.myapplication.network

import android.example.myapplication.model.Server
import android.example.myapplication.util.GenericApiResponse
import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ServersApiService {

    @GET("/v1/servers")
    @Headers("Content-type: application/json")
    fun getServers(@Header("Authorization") token: String): LiveData<GenericApiResponse<List<Server>>>
}



