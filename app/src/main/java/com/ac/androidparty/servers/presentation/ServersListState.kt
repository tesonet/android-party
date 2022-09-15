package com.ac.androidparty.servers.presentation

internal interface ServersListState {
    object Loading : ServersListState
    object Error : ServersListState
    data class Success(val ok: Boolean) : ServersListState
}
