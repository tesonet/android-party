package com.ac.androidparty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ac.androidparty.navigation.AndroidPartyAppDestination

@Composable
fun rememberAndroidPartyAppState(
    navController: NavHostController = rememberNavController()
): AndroidPartyAppState = remember(navController) {
    AndroidPartyAppState(navController)
}

class AndroidPartyAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigate(destination: AndroidPartyAppDestination, route: String? = null) {
        navController.navigate(route ?: destination.route)
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}