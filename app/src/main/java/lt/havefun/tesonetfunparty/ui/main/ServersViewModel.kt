package lt.havefun.tesonetfunparty.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import lt.havefun.tesonetfunparty.data.MainRepository
import lt.havefun.tesonetfunparty.data.Server

class ServersViewModel(
    private val mainRepository: MainRepository,
    private val cachedServersListRepository: CachedServersListRepository
): ViewModel() {
        @Suppress("UNCHECKED_CAST")
        class Factory(
            private val mainRepository: MainRepository,
            private val cachedServersListRepository: CachedServersListRepository
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ServersViewModel(
                    mainRepository = mainRepository,
                    cachedServersListRepository = cachedServersListRepository
                ) as T
            }
        }

    private val _data = MutableLiveData<List<Server>>()
    val data: LiveData<List<Server>> = _data

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var subscription = CompositeDisposable()

    fun getServersList() {
        mainRepository.getServers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    _data.postValue(it)
                    cachedServersListRepository.saveServers(it)
                } ?: run {
                    val cachedServers = cachedServersListRepository.getCachedServers()
                    cachedServers?.also {
                        _data.postValue(it)
                    } ?: _error.postValue("error")
                }
            }, { _ ->
                _error.postValue("error")
            })
            .also {
                subscription.add(it)
            }
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}