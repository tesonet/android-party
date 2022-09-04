package com.yasserakbbach.androidparty.listsevers.presenation

import com.yasserakbbach.androidparty.listsevers.domain.model.Server

data class ServerListState(
    val servers: List<Server> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
