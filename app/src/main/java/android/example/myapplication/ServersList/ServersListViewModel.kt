package android.example.myapplication.ServersList

import android.example.myapplication.ServersList.state.ServersListState
import android.example.myapplication.ServersList.state.ServersListStateEvent
import android.example.myapplication.model.Server
import android.example.myapplication.network.RetrofitBuilder
import android.example.myapplication.repository.ServersRepository
import android.example.myapplication.util.AbsentLiveData
import android.example.myapplication.util.DataState
import android.example.myapplication.util.GenericApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ServersListViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ServersListState> =MutableLiveData()

    val viewState: LiveData<ServersListState>
        get()=_viewState

    private val _stateEvent: MutableLiveData<ServersListStateEvent> =MutableLiveData()

    fun dataState(token: String): LiveData<DataState<ServersListState>> {
        return Transformations
            .switchMap(_stateEvent) { stateEvent ->
                stateEvent?.let {
                    handleStateEvent(stateEvent, token)
                }
            }
    }

    private fun handleStateEvent(
        stateEvent: ServersListStateEvent,
        token: String
    ): LiveData<DataState<ServersListState>>? {
        println("DEBUG: new state event detected $stateEvent")
        return when (stateEvent) {
            is ServersListStateEvent.GetServersEvent -> {
                ServersRepository.getServers(token)
            }
            is ServersListStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    private fun getCurrentViewStateOrNew(): ServersListState {
        return viewState.value?.let {
            it
        } ?: ServersListState()
    }

    fun setServersListData(servers: List<Server>) {
        val update=getCurrentViewStateOrNew()
        update.servers=servers
        _viewState.value=update
    }

    fun setStateEvent(event: ServersListStateEvent) {
        _stateEvent.value=event
    }

    //For testing API response:
    fun testApiResponse(token: String): LiveData<GenericApiResponse<List<Server>>> {
        return ServersRepository.testApiResponse(token)
    }
}