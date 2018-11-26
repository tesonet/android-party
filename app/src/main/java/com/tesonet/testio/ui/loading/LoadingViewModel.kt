package com.tesonet.testio.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.repository.CredentialsRepository
import com.tesonet.testio.data.repository.ServerRepository
import com.tesonet.testio.data.repository.TokenRepository
import javax.inject.Inject


class LoadingViewModel @Inject constructor(
    credentialsRepository: CredentialsRepository,
    tokenRepository: TokenRepository,
    private val serverRepository: ServerRepository
) : ViewModel() {

    private val token = Transformations.switchMap(credentialsRepository.getCredentials()) {
        if (it.status == Resource.Status.ERROR) {
            mapError(it)
        } else {
            tokenRepository.getToken(it.data!!)
        }
    }

    private val servers = Transformations.switchMap(token) {
        if (it.status == Resource.Status.ERROR) {
            mapError(it)
        } else {
            serverRepository.getServers(it.data!!)
        }
    }

    fun getServers(): LiveData<Resource<List<Server>>> = servers

    private fun <I, O> mapError(input: Resource<I>): LiveData<Resource<O>> {
        val liveData = MutableLiveData<Resource<O>>()
        liveData.value = Resource.error(input.exception!!)
        return liveData
    }
}