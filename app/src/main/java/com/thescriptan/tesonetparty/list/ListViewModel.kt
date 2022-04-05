package com.thescriptan.tesonetparty.list

import androidx.lifecycle.ViewModel
import com.thescriptan.tesonetparty.list.model.ServerInfo
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val navigator: Navigator) : ViewModel() {

    fun logout() {
        navigator.navigateTo(Screen.LOGIN)
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
}