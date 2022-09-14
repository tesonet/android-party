package com.ac.androidparty.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ac.androidparty.login.ui.LoginRoute
import com.ac.androidparty.navigation.AndroidPartyAppDestination

object LoginDestination
    : AndroidPartyAppDestination {
    override val route: String
        get() = "login"
}

fun NavGraphBuilder.loginGraph() {
    composable(route = LoginDestination.route) {
        LoginRoute()
    }
}