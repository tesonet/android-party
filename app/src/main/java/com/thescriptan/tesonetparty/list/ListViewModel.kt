package com.thescriptan.tesonetparty.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thescriptan.tesonetparty.list.model.ServerInfo
import com.thescriptan.tesonetparty.list.repository.ListRepository
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: ListRepository
) : ViewModel() {

    private val _listState = MutableStateFlow<ListState>(ListState.Authorized)
    val listState: StateFlow<ListState> = _listState

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.logout()
            _listState.value = ListState.Logout
        }
    }

    fun getServerList(): List<ServerInfo> {
        return listOf(
            ServerInfo("Lithuania #10", 25),
            ServerInfo("Canada #5", 25),
            ServerInfo("Italy #155", 100),
            ServerInfo("Lithuania #11", 2500),
            ServerInfo("Lithuania #2", 2500),
            ServerInfo("Lithuania #10", 25),
        )
    }

    fun navigateToLogin() {
        navigator.navigateTo(Screen.LOGIN)
    }
}