package gj.tesonet.ui.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.R
import gj.tesonet.data.model.Token
import gj.tesonet.data.model.User
import gj.tesonet.ui.AppViewModel
import gj.tesonet.ui.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.logging.Level

class LoginViewModel(application: Application): AppViewModel(application) {

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val _token = MutableLiveData<Token>()

    // login result, might be wrapped with message into Result type or so
    val token: LiveData<Token>
        get() = _token

    init {
        _user.value = app.user
        app.user = null
    }

    fun login(name: String, password: String) {
        if (name.isEmpty() || password.isEmpty()) {
            message(getString(R.string.msg_missing_login_data), Level.WARNING)

            return
        }

        viewModelScope.launch {
            val user = User(name, password)

            withContext(Dispatchers.IO) {
                try {
                    backend.login(user)
                } catch (e: Exception) {
                    Timber.e(e, "login failed")
                    null
                }
            }?.let {
                app.user = user
                _user.value = user
                _token.value = it
            } ?: run {
                message(getString(R.string.msg_failed_login), Level.SEVERE)
            }
        }
    }
}
