package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.data_flow.interactions.IServerInteractor
import com.teso.net.ui.base.BaseViewModel
import com.teso.net.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
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
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    serverInteractor.writeServersToDb(it)
                    nextScreen.postValue(true)
                }, {
                    error.value = ErrorModel(stringId = R.string.server_error)
                    nextScreen.postValue(true)
                }))
    }
}