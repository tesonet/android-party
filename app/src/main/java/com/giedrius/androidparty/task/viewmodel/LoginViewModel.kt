package com.giedrius.androidparty.task.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.server.login.LoginOutcome
import com.giedrius.androidparty.task.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(val repository: Repository) : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    fun login(apiListener: ApiListener<LoginOutcome>, username: String, password: String) {
            scope.launch { repository.getToken(username, password, object : ApiListener<LoginOutcome> {
                        override fun <T> onResult(data: T) {
                            apiListener.onResult(data)
                        }
                    })
            }
    }
}