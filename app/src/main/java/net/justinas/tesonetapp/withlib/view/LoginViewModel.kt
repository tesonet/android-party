package net.justinas.tesonetapp.withlib.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import net.justinas.minilist.domain.user.UserRepository
import net.justinas.minilist.util.LoadResult
import net.justinas.minilist.util.SingleLiveEvent

class LoginViewModel(val userRepo: UserRepository) : ViewModel() {

    val disposable = CompositeDisposable()

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val successLogin = SingleLiveEvent<Any>()

    val result = MutableLiveData<LoadResult<Boolean>>()

    fun onLoginClick() {
        val username1 = username.value
        val password1 = password.value
        if (!username1.isNullOrBlank() && !password1.isNullOrBlank()) {
            result.postValue(LoadResult.Loading)
            disposable.add(
                userRepo.login(username1, password1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {
                            result.postValue(LoadResult.Success(true))
                            successLogin.call()
                        },
                        onError = {
                            result.postValue(LoadResult.Error(it))
                        }
                    ))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}