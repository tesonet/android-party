package android.example.myapplication.network

import android.example.myapplication.model.Server
import android.example.myapplication.util.GenericApiResponse
import androidx.lifecycle.LiveData
import retrofit2.http.GET


interface ServersApiService {
    @GET("/v1/servers?username=tesonet&password=partyanimal")
    fun getServers(): LiveData<GenericApiResponse<List<Server>>>
}



