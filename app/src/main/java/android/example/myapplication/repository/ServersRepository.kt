package android.example.myapplication.repository

import android.example.myapplication.ServersList.state.ServersListState
import android.example.myapplication.model.Server
import android.example.myapplication.network.RetrofitBuilder
import android.example.myapplication.util.ApiSuccessResponse
import android.example.myapplication.util.GenericApiResponse
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job
import android.example.myapplication.util.DataState as DataState

object ServersRepository {

    private var repositoryJob: Job?=null

    fun getServers(token: String): LiveData<DataState<ServersListState>> {
        return object : NetworkBoundResource<List<Server>, ServersListState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Server>>) {
                onCompleteJob(
                    DataState.data(
                        data=ServersListState(servers=response.body)
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Server>>> {
                return RetrofitBuilder.apiService.getServers(token)
            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob=job
            }
        }.asLiveData()
    }

    fun cancelActiveJobs() {
        println("DEBUG Servers Repo: Cancelling on-going jobs...")
        repositoryJob?.cancel()
    }

    //Method for testing purposes:
    fun testApiResponse(token: String): LiveData<GenericApiResponse<List<Server>>> {
        return RetrofitBuilder.apiService.getServers(token)
    }
}