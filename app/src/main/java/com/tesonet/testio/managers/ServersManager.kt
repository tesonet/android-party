package com.tesonet.testio.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tesonet.testio.utils.Resource
import com.tesonet.testio.utils.Resource.Complete
import com.tesonet.testio.utils.Resource.Empty
import com.tesonet.testio.utils.Resource.Error
import com.tesonet.testio.service.data.user.RequestUser
import com.tesonet.testio.service.repositories.ServersRepository
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit.SECONDS

class ServersManager(private val serversRepository: ServersRepository) {

    private val _compositeDisposable = CompositeDisposable()

    private var _requestToken = MutableLiveData<Resource<String>>()
    val requestToken: LiveData<Resource<String>>
        get() = _requestToken

    fun getAccessToken(requestUser: RequestUser) {
        _requestToken.value = Resource.Loading()
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
}