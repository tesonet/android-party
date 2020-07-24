package gj.tesonet.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.App
import gj.tesonet.R
import gj.tesonet.backend.Backend
import gj.tesonet.data.Data
import gj.tesonet.data.model.Server
import gj.tesonet.data.model.Token
import gj.tesonet.data.model.User
import gj.tesonet.ui.AppViewModel
import gj.tesonet.ui.Message
import gj.tesonet.ui.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import java.util.logging.Level

class LoginViewModel(application: Application): AppViewModel(application) {

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val _token = MutableLiveData<Token>()

    val token: LiveData<Token>
        get() = _token

    init {
        _user.value = app.user
        app.user = null
    }

    fun login(name: String, password: String) {
        if (name.isEmpty() || password.isEmpty()) {
            _message.value = Message(getString(R.string.msg_missing_login_data), Level.WARNING)

            return
        }

        viewModelScope.launch {
            val user = User(name, password)

            withContext(Dispatchers.IO) {
                try {
                    app.backend.login(user)
                } catch (e: Exception) {
                    Timber.e(e, "login failed")
                    null
                }
            }?.let {
                app.user = user
                _user.value = user
                _token.value = it
            } ?: run {
                _message.value = Message(getString(R.string.msg_failed_login), Level.SEVERE)
            }
        }
    }
}
