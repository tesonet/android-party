package net.justinas.tesonetapp.withlib.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import net.justinas.minilist.domain.item.GetListItemsInteractor
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.util.LoadResult
import java.util.concurrent.TimeUnit

class ListViewModel(private val getListItemsInteractor: GetListItemsInteractor) : ViewModel() {

    var result = MutableLiveData<LoadResult<List<IdEntity>>>()

    private val disposable = CompositeDisposable()

    init {
            getList()
    }

    fun retry() {
        getList()
    }

    private fun getList() {
        result.postValue(LoadResult.Loading)
        disposable.add(
            getListItemsInteractor.execute(GetListItemsInteractor.Request(0))
                .delay(2, TimeUnit.SECONDS)
                .subscribeBy(
                    onSuccess = {
                        result.postValue(LoadResult.Success(it))
                    },
                    onError = { result.postValue(LoadResult.Error(it)) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}