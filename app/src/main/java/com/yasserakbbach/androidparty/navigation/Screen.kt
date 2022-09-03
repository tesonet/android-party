package com.yasserakbbach.androidparty.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object ListServer : Screen("list_server_screen")
}
