package com.tesonet.testio.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tesonet.testio.base.Resource
import com.tesonet.testio.base.Resource.Status.ERROR
import com.tesonet.testio.base.Resource.Status.SUCCESS
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.repository.CredentialsRepository
import com.tesonet.testio.data.repository.ServerRepository
import com.tesonet.testio.data.repository.TokenRepository
import javax.inject.Inject

class LoadingViewModel @Inject constructor(
    credentialsRepository: CredentialsRepository,
    tokenRepository: TokenRepository,
    serverRepository: ServerRepository
) : ViewModel() {

    private val token = Transformations.switchMap(credentialsRepository.getCredentials()) {
        if (it.status == SUCCESS) {
            tokenRepository.getToken(it.data!!)
        } else {
            mapResource(it)
        }
    }

    private val servers = Transformations.switchMap(token) {
        if (it.status == SUCCESS) {
            serverRepository.getServers(it.data!!)
        } else {
            mapResource(it)
        }
    }

    fun getServers(): LiveData<Resource<List<Server>>> = servers

    private fun <I, O> mapResource(input: Resource<I>): LiveData<Resource<O>> {
        val liveData = MutableLiveData<Resource<O>>()
        liveData.value = if (input.status == ERROR) Resource.error(input.exception!!) else Resource.loading()
        return liveData
    }
}