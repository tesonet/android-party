package com.ac.androidparty.servers.viewmodel

internal interface ServersEvent {
    object Refresh: ServersEvent
    object Logout: ServersEvent
}