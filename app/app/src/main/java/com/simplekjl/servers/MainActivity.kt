package com.simplekjl.servers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simplekjl.servers.navigation.NavTarget
import com.simplekjl.servers.navigation.Navigator
import com.simplekjl.servers.ui.list.ServerListScreen
import com.simplekjl.servers.ui.login.LoginScreen
import com.simplekjl.servers.ui.theme.ServersTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigator: Navigator by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServersTheme {
                val navController = rememberNavController()
                LaunchedEffect("navigation") {
                    navigator.sharedFlow.onEach {
                        navController.navigate(it.label) {
                            if (it.label == NavTarget.Login.label)
                                popUpTo(NavTarget.ServerList.label) { inclusive = true }
                            else if (it.label == NavTarget.ServerList.label)
                                popUpTo(NavTarget.Login.label) { inclusive = true }
                        }
                    }.launchIn(this)
                }
                NavHost(navController = navController, startDestination = NavTarget.Login.label) {
                    composable(NavTarget.Login.label) {
                        LoginScreen()
                    }
                    composable(NavTarget.ServerList.label) {
                        ServerListScreen()
                    }
                }
            }
        }
    }
}
