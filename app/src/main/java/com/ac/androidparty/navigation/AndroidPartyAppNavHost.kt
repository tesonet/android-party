package com.ac.androidparty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ac.androidparty.login.navigation.LoginDestination
import com.ac.androidparty.login.navigation.loginGraph

@Composable
fun AndroidPartyAppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AndroidPartyAppDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = LoginDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        loginGraph()
    }
}