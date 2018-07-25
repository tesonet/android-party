package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.ErrorModel
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.data_flow.interactions.IServerInteractor
import com.teso.net.ui.base.BaseViewModel
import com.teso.net.utils.SingleLiveEvent
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginFragmentVM : BaseViewModel() {

    @Inject lateinit var serverInteractor: IServerInteractor

    private val error: SingleLiveEvent<ErrorModel> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
    }

    fun getListOfSites(): LiveData<List<ServerEntity>> = serverInteractor.getListOfSites()

    fun updateListOfSites() {
        disposal.add(serverInteractor.updateListOfServers()
                .subscribeOn(Schedulers.io())
                .subscribe({ serverInteractor.writeServersToDb(it) },
                        { error.postValue(ErrorModel(throwable = it)) }))
    }

    fun getError(): LiveData<ErrorModel> = error
}