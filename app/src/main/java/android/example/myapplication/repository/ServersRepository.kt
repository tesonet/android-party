package android.example.myapplication.repository

import android.example.myapplication.ServersList.state.ServersListState
import android.example.myapplication.model.Server
import android.example.myapplication.network.RetrofitBuilder
import android.example.myapplication.util.ApiSuccessResponse
import android.example.myapplication.util.GenericApiResponse
import androidx.lifecycle.LiveData
import android.example.myapplication.util.DataState as DataState

object ServersRepository {

    fun getServers(token: String): LiveData<DataState<ServersListState>> {
        return object : NetworkBoundResource<List<Server>, ServersListState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Server>>) {
                result.value = DataState.data(
                    null,
                    ServersListState(
                        servers=response.body

                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Server>>> {
                return RetrofitBuilder.apiService.getServers(token)
            }
        }.asLiveData()
    }

    //Method for testing purposes:
    fun testApiResponse(token: String): LiveData<GenericApiResponse<List<Server>>> {
        return RetrofitBuilder.apiService.getServers(token)
    }
}