package gj.tesonet.ui

import android.app.Application
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

open class AppViewModel(application: Application): AndroidViewModel(application) {

    protected val _message = MutableLiveData<Message>()

    val message: LiveData<Message>
        get() = _message

    protected val app: App
        get() = getApplication<App>()

}
