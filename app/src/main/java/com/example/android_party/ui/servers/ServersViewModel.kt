package com.example.android_party.ui.servers

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.android_party.BaseViewModel
import com.example.android_party.api.TesonetApi
import com.example.android_party.data.Server
import com.example.android_party.ui.login.LoginRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServersViewModel : BaseViewModel() {

    private val TAG = ServersViewModel::class.java.simpleName

    @Inject
    lateinit var tesonetApi: TesonetApi

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var serversRepository: ServersRepository

    private lateinit var subscription: Disposable

    val performLogout = MutableLiveData<Boolean>()
    val loadingSuccess = MutableLiveData<List<Server>>()
    val loadingError = MutableLiveData<String>()

    val serversVisibility = ObservableBoolean()

    init {
        fetchServers()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun onLogoutButtonClicked(view: View) {
        processLogout()
    }

    private fun processLogout() {
        subscription = Observable.fromCallable { loginRepository.deleteToken() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { e -> Log.e(TAG, e.message.toString()) }
            .subscribe()
        subscription = Observable.fromCallable { serversRepository.deleteServersData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { e -> Log.e(TAG, e.message.toString()) }
            .subscribe()
        performLogout.postValue(true)
    }

    private fun fetchServers() {
        subscription = Observable.fromCallable {
            serversRepository.getServerListFromDb()
        }.concatMap { dbServersList ->
            if (dbServersList.isEmpty())
                serversRepository.getServerListFromApi(tesonetApi, loginRepository.getToken())
                    .concatMap { apiServersList ->
                        serversRepository.storeServersList(apiServersList)
                        Observable.just(apiServersList)
                    }
            else
                Observable.just(dbServersList)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> onRetrieveServersListSuccess(result) },
                { e -> onRetrieveServersListError(e) })
    }

    private fun onRetrieveServersListSuccess(response: List<Server>) {
        loadingSuccess.postValue(response)
        serversVisibility.set(true)
    }

    private fun onRetrieveServersListError(error: Throwable) {
        Log.e(TAG, error.message.toString())
        loadingError.postValue(error.localizedMessage)
        processLogout()
    }
}