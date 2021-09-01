package com.tesonet.testio.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tesonet.testio.services.data.server.Server
import com.tesonet.testio.services.data.user.RequestUser
import com.tesonet.testio.services.repositories.ServersRepository
import com.tesonet.testio.utils.Resource
import com.tesonet.testio.utils.Resource.Complete
import com.tesonet.testio.utils.Resource.Empty
import com.tesonet.testio.utils.Resource.Error
import com.tesonet.testio.utils.Resource.Loading
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit.SECONDS

class ServersManager(private val serversRepository: ServersRepository) {

    private val _compositeDisposable = CompositeDisposable()

    private val _requestToken = MutableLiveData<Resource<String>>()
    val requestToken: LiveData<Resource<String>>
        get() = _requestToken

    private val _savedServersState = MutableLiveData<Resource<Boolean>>()
    val savedServersState: MutableLiveData<Resource<Boolean>>
        get() = _savedServersState

    private val _servers = MutableLiveData<Resource<List<Server>>>()
    val servers: MutableLiveData<Resource<List<Server>>>
        get() = _servers

    fun getAccessToken(requestUser: RequestUser) {
        _requestToken.value = Loading()
        serversRepository.getTokenAccess(requestUser)
            .delay(3, SECONDS) // for demo purpose
            .subscribe(
                { response ->
                    _requestToken.postValue(
                        if (!response.token.isNullOrEmpty()) {
                            Complete(response.token)
                        } else {
                            Empty()
                        }
                    )
                },
                { error ->
                    _requestToken.postValue(Error(error.localizedMessage))
                }
            ).also {
                _compositeDisposable.add(it)
            }
    }

    fun fetchServers(token: String) {
        deleteToken()
        serversRepository.getServerList(token)
            .subscribe(
                { response ->
                    if (!response.isNullOrEmpty()) {
                        serversRepository.saveServersToDatabase(response) {
                            _savedServersState.value = Complete(it)
                        }
                    }
                },
                { error ->
                    _savedServersState.value = Error(error.message)
                }
            ).also { _compositeDisposable.addAll(it) }
    }

    fun getServersFromDatabase() {
        _servers.value = Loading()
        serversRepository.getServersFromDatabase()
            .subscribe(
                { servers ->
                    if (!servers.isNullOrEmpty()) {
                        _servers.value = Complete(servers)
                    } else {
                        _servers.value = Empty()
                    }
                },
                { error ->
                    _servers.value = Error(error.message)
                }
            ).also {
                _compositeDisposable.addAll(it)
            }
    }

    fun deleteAllServersFromDatabase() {
        serversRepository.deleteAllServersFromDatabase()
            .subscribe(
                { sucess ->
                    resetServers()
                },
                { error ->
                    error
                }
            ).also {
                _compositeDisposable.addAll(it)
            }
    }

    fun resetServers() {
        _savedServersState.value = null
        _servers.value = Empty()
    }

    private fun deleteToken() {
        _requestToken.value = null
    }
}