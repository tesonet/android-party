package com.ac.androidparty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ac.androidparty.login.domain.preferences.LoginPreferences
import com.ac.androidparty.login.navigation.LoginDestination
import com.ac.androidparty.login.navigation.loginGraph
import com.ac.androidparty.servers.navigation.ServersListDestination
import com.ac.androidparty.servers.navigation.serversGraph

@Composable
fun AndroidPartyAppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AndroidPartyAppDestination) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = LoginDestination.route
) {
    val loginPreferences = remember { LoginPreferences() }

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = if (loginPreferences.isTokenAvailable.not())
            startDestination else ServersListDestination.route,
    ) {
        loginGraph(
            navigateToServers = {
                onNavigateToDestination(ServersListDestination)
            }
        )
        serversGraph(
            navigateToLogin = {
                navController.clearBackStack(ServersListDestination.route)
                onNavigateToDestination(LoginDestination)
            },
            onBackPressed = onBackPressed
        )
    }
}