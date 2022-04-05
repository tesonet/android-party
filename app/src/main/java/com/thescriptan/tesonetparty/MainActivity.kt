package com.thescriptan.tesonetparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thescriptan.tesonetparty.list.ListScreen
import com.thescriptan.tesonetparty.login.LoginScreen
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import com.thescriptan.tesonetparty.ui.theme.TesonetPartyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesonetPartyTheme {
                val navController = rememberNavController()
                SingleActivity(navController = navController, navigator = navigator)
            }
        }
    }
}

@Composable
fun SingleActivity(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.label)
        }.launchIn(this)
    }
    NavHost(navController = navController, startDestination = Screen.LOGIN.label) {
        composable(Screen.LOGIN.label) {
            LoginScreen()
        }
        composable(Screen.LIST.label) {
            ListScreen(navigator)
        }
    }
}
