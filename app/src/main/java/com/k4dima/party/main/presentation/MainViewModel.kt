package com.k4dima.androidparty.features.main.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.k4dima.party.main.data.model.Server
import com.k4dima.androidparty.features.main.domain.ServersUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel
@Inject
constructor(private val useCase: ServersUseCase) : ViewModel() {
    //val adapter = ServersAdapter()
    val failure = MutableLiveData<String>()
    val servers = object : MutableLiveData<List<Server>>() {
        override fun onActive() {
            if (value == null)
                disposable = useCase.data(Unit)
                        .subscribe({ postValue(it) },
                                { failure.postValue(it.localizedMessage) })
        }
    }
    private lateinit var disposable: Disposable

    fun logout() {
        useCase.logout()
        //servers.value = null
        failure.value = "logout"
    }

    override fun onCleared() = disposable.dispose()
}