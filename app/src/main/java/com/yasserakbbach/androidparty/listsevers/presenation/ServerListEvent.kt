package com.yasserakbbach.androidparty.listsevers.presenation

sealed class ServerListEvent {
    object OnLogoutClick : ServerListEvent()
    object OnSwipeRefresh : ServerListEvent()
}
