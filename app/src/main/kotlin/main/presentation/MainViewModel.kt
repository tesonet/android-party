package main.presentation

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.DataRepository
import app.domain.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import main.domain.model.Server
import main.ui.ServersAdapter
import timber.log.Timber
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val serversRepository: DataRepository<String, List<Server>>,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    val login = MutableLiveData<Unit>()
    val error = MutableLiveData<String?>()
    val servers = ObservableField<List<Server>>()
    val refreshing = ObservableBoolean()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                error.value = null
                refreshing.set(true)
                val serverList = withContext(Dispatchers.IO) {
                    val token = preferenceRepository.token ?: throw NoTokenException()
                    serversRepository.data(token)
                }
                servers.set(serverList)
            } catch (e: Exception) {
                Timber.e(e)
                if (e is NoTokenException) loginScreen()
                else error.value = e.localizedMessage ?: e.javaClass.simpleName
            } finally {
                refreshing.set(false)
            }
        }
    }

    fun loginScreen() {
        preferenceRepository.token = null
        login.value = Unit
    }
}