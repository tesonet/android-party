package com.yasserakbbach.androidparty.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yasserakbbach.androidparty.login.presentation.LoginScreen

@ExperimentalMaterial3Api
@Composable
fun AndroidPartyNavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.ListServer.route) {
            Text(text = "List servers...")
        }
    }
}