package com.ac.androidparty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
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
    fun navigate(destination: AndroidPartyAppDestination, route: String? = null) {
        navController.navigate(route ?: destination.route)
    }
}