package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.data_flow.interactions.ILoginInteractor
import com.teso.net.data_flow.interactions.ITokenInteractor
import com.teso.net.data_flow.network.api_models.TokenAnswer
import com.teso.net.ui.base.BaseViewModel
import com.teso.net.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginFragmentVM : BaseViewModel() {

    @Inject lateinit var tokenInteractor: ITokenInteractor

    @Inject lateinit var loginInteractor: ILoginInteractor

    private val error: SingleLiveEvent<ErrorModel> = SingleLiveEvent()

    private val nextScreen: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
    }

    fun getError(): LiveData<ErrorModel> = error

    fun getNextScreen(): LiveData<Boolean> = nextScreen

    fun login(name: String, password: String) {
        if (name.isBlank() || password.isBlank()) {
            error.value = ErrorModel(stringId = R.string.fields_empty)
        } else {
            disposal.add(loginInteractor.getTokenFormServer(name, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ saveToken(it, name, password) },
                            { error.value = ErrorModel(stringId = R.string.incorrect_password) }))
        }
    }

    private fun saveToken(tokenAnswer: TokenAnswer, name: String, password: String) {
        if (tokenAnswer.token?.isBlank() == false) {
            tokenInteractor.setToken(tokenAnswer.token)
            tokenInteractor.setUserName(name)
            tokenInteractor.setPassword(password)
            nextScreen.postValue(true)
        } else {
            error.value = ErrorModel(stringId = R.string.server_error)
        }
    }

    fun checkIfUserAlreadyLogin() {
        if (tokenInteractor.hasPassword().and(tokenInteractor.hasUserName())) {
            Timber.d("User already login")
            nextScreen.postValue(true)
        }
    }
}