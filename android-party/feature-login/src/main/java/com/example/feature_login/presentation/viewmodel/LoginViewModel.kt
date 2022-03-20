package com.example.feature_login.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.BaseViewModel
import com.example.domain_login.domain.usecase.LoginUseCase
import com.example.feature_login.R
import com.example.feature_login.presentation.viewmodel.LoginContract.Event.OnLoginClicked
import com.example.feature_login.presentation.viewmodel.LoginContract.Event.OnLoginSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    companion object {
        private const val USER_NAME_KEY = "username"
    }

    private fun login(userName: String, password: String) {
        // can be launched in a separate asynchronous job
        if (userName.isEmpty()) {
            sendEffect { LoginContract.Effect.UserNameErrorEffect(R.string.invalid_username) }
            return
        } else if (password.isEmpty()) {
            sendEffect { LoginContract.Effect.PasswordErrorEffect(R.string.invalid_password) }
            return
        }
        savedStateHandle.set(USER_NAME_KEY, userName)
        updateState { copy(isLoading = true, userName = userName, password = password) }
        viewModelScope.launch {
            loginUseCase.execute(
                LoginUseCase.Input(userName, password)
            ).collect { output ->
                when (output) {
                    is LoginUseCase.Output.Success -> {
                        onUiEvent(OnLoginSuccess)
                    }
                    is LoginUseCase.Output.NetworkError -> {
                        sendEffect { LoginContract.Effect.NetworkErrorEffect }
                    }
                    is LoginUseCase.Output.UnknownError -> {
                        sendEffect { LoginContract.Effect.UnknownErrorEffect(output.message) }
                    }
                }
                updateState { copy(isLoading = false) }
            }
        }
    }

    override fun provideInitialState(): LoginContract.State {
        val userName = savedStateHandle.get<String>(USER_NAME_KEY)
        return LoginContract.State(userName = userName.orEmpty())
    }

    override fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is OnLoginClicked -> {
                login(event.userName, event.password)
            }
            is OnLoginSuccess -> {
                sendEffect { LoginContract.Effect.OnNavigationEffect }
            }
        }
    }

/*    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }*/


}