package com.jonastiskus.testio.viewmodel

import androidx.lifecycle.*
import com.jonastiskus.testio.repository.room.entity.ServerEntity
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.network.service.ServiceProvider
import com.jonastiskus.testio.repository.resource.ServerResource

class ServerViewModel(private val serviceProvider: ServiceProvider,private val token : String) : ViewModel() {

    private val listMediatorLiveData = MediatorLiveData<List<ServerGsonModel>>()
    val listLiveData: LiveData<List<ServerGsonModel>> = listMediatorLiveData

    private val exceptionMediatorLiveData = MediatorLiveData<Throwable>()
    val exception: LiveData<Throwable> = exceptionMediatorLiveData

    private val cachedDataMediatorLiveData = MediatorLiveData<ServerEntity>()
    val cachedDataListLiveData = cachedDataMediatorLiveData

    fun load() {
        val resource = ServerResource(serviceProvider, token)

        listMediatorLiveData.addSource(resource.listLiveData) { t -> listMediatorLiveData.value = t }
        exceptionMediatorLiveData.addSource(resource.exception) { t ->  exceptionMediatorLiveData.value = t }
        cachedDataMediatorLiveData.addSource(resource.cachedListLiveData) {
            t ->  cachedDataMediatorLiveData.value = t
        }

        resource.load()
    }


    class ServiewViewModelFactory(private val serviceProvider: ServiceProvider,
                                  private val token : String) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ServerViewModel(serviceProvider, token) as T
        }

    }

}