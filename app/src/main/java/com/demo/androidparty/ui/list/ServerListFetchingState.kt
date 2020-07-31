package com.demo.androidparty.ui.list

import com.demo.androidparty.dto.Server

internal sealed class ServerListFetchingState {
    object Started : ServerListFetchingState()
    class Failed(val reason: String) : ServerListFetchingState()
    class Success(val data: List<Server>) : ServerListFetchingState()
}