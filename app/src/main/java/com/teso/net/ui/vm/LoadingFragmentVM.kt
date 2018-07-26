package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.data_flow.interactions.IServerInteractor
import com.teso.net.ui.base.BaseViewModel
import com.teso.net.utils.SingleLiveEvent
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadingFragmentVM : BaseViewModel() {


    @Inject lateinit var serverInteractor: IServerInteractor

    private val error: SingleLiveEvent<ErrorModel> = SingleLiveEvent()

    private val nextScreen: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
    }

    fun getError(): LiveData<ErrorModel> = error

    fun getNextScreen(): LiveData<Boolean> = nextScreen

    fun fetchingList() {
        disposal.add(serverInteractor.updateListOfServers()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    serverInteractor.writeServersToDb(it)
                    nextScreen.postValue(true)
                }, {
                    error.value = ErrorModel(stringId = R.string.server_error)
                    nextScreen.postValue(true)
                }))
    }
}