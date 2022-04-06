package com.thescriptan.tesonetparty.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thescriptan.tesonetparty.list.model.ServerInfo
import com.thescriptan.tesonetparty.list.repository.ListRepository
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import com.thescriptan.tesonetparty.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: ListRepository
) : ViewModel() {

    private val _serverList = MutableStateFlow<List<ServerInfo>>(listOf())
    val serverList: StateFlow<List<ServerInfo>> = _serverList

    private val _listState = MutableStateFlow<ListState>(ListState.Authorized)
    val listState: StateFlow<ListState> = _listState

    private val _errorMessage = MutableSharedFlow<String>(replay = 1)
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        getServerList()
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.logout()
            _listState.value = ListState.Logout
        }
    }

    fun getServerList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val serversResult = repository.getServers()) {
                is Result.Error -> {
                    val errorMessage = "${serversResult.message}"
                    _errorMessage.tryEmit(errorMessage)
                }
                is Result.Loading -> TODO()
                is Result.Success -> _serverList.value = serversResult.data ?: listOf()
            }
        }
    }

    fun navigateToLogin() {
        navigator.navigateTo(Screen.LOGIN)
    }
}