package com.baruckis.androidparty.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.usecases.FetchServersUseCase
import com.baruckis.androidparty.domain.usecases.LogoutUseCase
import com.baruckis.androidparty.presentation.mapper.ServerPresentationMapper
import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val fetchServersUseCase: FetchServersUseCase,
    private val serverPresentationMapper: ServerPresentationMapper
) : ViewModel() {

    private val _serversResource = MutableLiveData<Resource<List<ServerPresentation>>>()
    val serversResource: LiveData<Resource<List<ServerPresentation>>> = _serversResource

    fun logoutClick() {
        logoutUseCase.execute()
    }

    fun fetchServersLocally() {
        fetchServersUseCase.execute(
            GetServersSubscriber(),
            FetchServersUseCase.DataSource.LOCAL
        )
    }

    private inner class GetServersSubscriber : DisposableSingleObserver<List<ServerEntity>>() {

        override fun onSuccess(serversList: List<ServerEntity>) {
            _serversResource.postValue(
                Resource(
                    Status.SUCCESS,
                    serversList.map { server -> serverPresentationMapper.mapToPresentation(server) },
                    null
                )
            )
        }

        override fun onError(e: Throwable) {
            _serversResource.postValue(
                Resource(
                    Status.ERROR,
                    null,
                    e.localizedMessage
                )
            )
        }

    }

}