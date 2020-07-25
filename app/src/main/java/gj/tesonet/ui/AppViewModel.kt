package gj.tesonet.ui

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.App
import gj.tesonet.backend.Backend
import gj.tesonet.data.Data
import gj.tesonet.data.model.Server
import gj.tesonet.data.model.User
import gj.tesonet.ui.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicReference
import java.util.logging.Level

open class AppViewModel(application: Application): AndroidViewModel(application) {

    protected val _message = MutableLiveData<AtomicReference<Message>>(AtomicReference())

    // atomic reference enables a trick to implement consume-once behaviour
    val message: LiveData<AtomicReference<Message>>
        get() = _message

    protected val app: App
        get() = getApplication<App>()

    protected fun message(text: String, level: Level) {
        _message.value = AtomicReference(Message(text, level))
    }

    protected val backend: Backend by lazy {
        Backend.create()
    }

    protected val data: Data by lazy {
        app.data
    }
}

inline fun AndroidViewModel.getString(@StringRes id: Int): String {
    return getApplication<Application>().getString(id)
}
