package android.example.myapplication.ServersList

import android.example.myapplication.ServersList.state.ServersListState
import android.example.myapplication.ServersList.state.ServersListStateEvent
import android.example.myapplication.model.Server
import android.example.myapplication.repository.ServersRepository
import android.example.myapplication.util.AbsentLiveData
import android.example.myapplication.util.DataState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ServersListViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ServersListState> =MutableLiveData()

    val viewState: LiveData<ServersListState>
        get()=_viewState

    private val _stateEvent: MutableLiveData<ServersListStateEvent> =MutableLiveData()

    val dataState: LiveData<DataState<ServersListState>> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: ServersListStateEvent):LiveData<DataState<ServersListState>>? {
        println("DEBUG: new state event detected $stateEvent")
        return when (stateEvent) {
            is ServersListStateEvent.GetServersEvent -> {
                return ServersRepository.getServers()
            }
            is ServersListStateEvent.None -> {
              return AbsentLiveData.create()
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

    fun setStateEvent(event:ServersListStateEvent){
        _stateEvent.value = event
    }
}