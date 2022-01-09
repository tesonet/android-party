package com.example.androidParty.presentation.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidParty.authentication.GetTokenUseCase
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.domain.entity.User
import com.example.androidParty.presentation.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val getToken: GetTokenUseCase
) : ViewModel() {
    private val _loginResponse: MutableLiveData<Resource<User>> = MutableLiveData()
    val loginResponse: LiveData<Resource<User>>
        get() = _loginResponse

    init {
        checkAlreadyLoggedIn()
    }

    private fun checkAlreadyLoggedIn() {
        viewModelScope.launch {
            val token = getToken().first()
            token?.let {
                _loginResponse.value = Resource.Success(User(token = token))
            }
        }
    }

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val paramObject = JSONObject()
            paramObject.put("username", userName)
            paramObject.put("password", password)
            val body: RequestBody =
                RequestBody.create(MediaType.parse("application/json"), paramObject.toString())

            login(body).onEach { user ->
                when (user) {
                    is Resource.Error -> _loginResponse.value =
                        Resource.Error("Something Went Wrong")
                    is Resource.Loading -> _loginResponse.value = Resource.Loading()
                    is Resource.Success -> {
                        _loginResponse.value = user
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}