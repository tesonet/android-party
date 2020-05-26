package com.baruckis.androidparty.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.usecases.LoginUseCase
import com.baruckis.androidparty.presentation.mapper.LoginPresentationMapper
import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.presentation.state.Resource
import com.baruckis.androidparty.presentation.state.Status
import com.baruckis.androidparty.presentation.util.logConsoleVerbose
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val loginPresentationMapper: LoginPresentationMapper
) : ViewModel() {

    private val _loginResource = MutableLiveData<Resource<LoginPresentation>>()
    val loginResource: LiveData<Resource<LoginPresentation>> = _loginResource

    private lateinit var username: String
    private lateinit var password: String

    private var isLoggedIn: Boolean = false

    fun login(username: String, password: String, delayTime: Long = DELAY) {
        _loginResource.postValue(Resource(Status.LOADING, null, null))

        this.username = username
        this.password = password

        // Start a coroutine
        GlobalScope.launch {
            delay(delayTime)
            // do something after time delay
            loginUseCase.execute(
                LoginSubscriber(),
                LoginUseCase.Params.authorization(username, password)
            )
        }
    }

    fun retryButtonClick() {
        if (isLoggedIn) {
            // TODO fetch list.
        } else {
            login(username, password)
        }
    }

    override fun onCleared() {
        loginUseCase.dispose()
        super.onCleared()
    }

    inner class LoginSubscriber : DisposableSingleObserver<LoggedInUserEntity>() {

        override fun onSuccess(loggedInUser: LoggedInUserEntity) {
            isLoggedIn = true

            _loginResource.postValue(
                Resource(
                    Status.SUCCESS,
                    loginPresentationMapper.mapTo(loggedInUser),
                    null
                )
            )

            //logConsoleVerbose("onSuccess - Token " + loggedInUser.token)
        }

        override fun onError(e: Throwable) {
            isLoggedIn = false

            _loginResource.postValue(
                Resource(
                    Status.ERROR,
                    null,
                    e.localizedMessage
                )
            )

            //logConsoleVerbose("onError - " + e.localizedMessage)
        }

    }

    companion object {
        private const val DELAY: Long = 1000
    }

}