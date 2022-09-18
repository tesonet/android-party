package com.ac.androidparty.servers.presentation

import com.ac.androidparty.servers.domain.model.Server

internal interface ServersListState {
    object Loading : ServersListState
    data class Error(val servers: List<Server> = emptyList()) : ServersListState
    data class Success(val servers: List<Server>) : ServersListState
}
