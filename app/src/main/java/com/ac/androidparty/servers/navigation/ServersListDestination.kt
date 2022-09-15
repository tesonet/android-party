package com.ac.androidparty.servers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ac.androidparty.navigation.AndroidPartyAppDestination
import com.ac.androidparty.servers.presentation.ui.ServersListRoute

object ServersListDestination : AndroidPartyAppDestination {
    override val route: String
        get() = "servers_list"
}

fun NavGraphBuilder.serversGraph() = composable(route = ServersListDestination.route) {
    ServersListRoute()
}