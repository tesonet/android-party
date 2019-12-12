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
    var username: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()

    fun login(apiListener: ApiListener<LoginOutcome>) {
        val userName = username.get()
        val pass = password.get()
        if (userName != null && pass != null) {
            scope.launch { repository.getToken(userName, pass, object :
                        ApiListener<LoginOutcome> {
                        override fun <T> onResult(data: T) {
                            apiListener.onResult(data)
                        }
                    })
            }
        }
    }
}