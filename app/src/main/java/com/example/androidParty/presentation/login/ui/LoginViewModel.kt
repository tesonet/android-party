package com.example.androidParty.presentation.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidParty.authentication.GetTokenUseCase
import com.example.androidParty.datalayer.LoginCredentials
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.login.domain.entity.User
import com.example.androidParty.presentation.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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

      val loginCredentials = LoginCredentials(userName, password)
      login(loginCredentials).collect { user ->
        when (user) {
          is Resource.Error -> {
            _loginResponse.value = user
          }
          is Resource.Loading -> _loginResponse.value = Resource.Loading()
          is Resource.Success -> {
            _loginResponse.value = user
          }
        }
      }
    }
  }
}