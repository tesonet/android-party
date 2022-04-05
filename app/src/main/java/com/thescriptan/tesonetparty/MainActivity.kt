package com.thescriptan.tesonetparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thescriptan.tesonetparty.list.ListScreen
import com.thescriptan.tesonetparty.login.LoginScreen
import com.thescriptan.tesonetparty.nav.Navigator
import com.thescriptan.tesonetparty.nav.Screen
import com.thescriptan.tesonetparty.ui.theme.TesonetPartyTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesonetPartyTheme {
                val navController = rememberNavController()
                val navigator = Navigator()
                SingleActivity(navController = navController, navigator = navigator)
            }
        }
    }
}

@Composable
fun SingleActivity(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navigator.navigateTo(it)
        }.launchIn(this)
    }
    NavHost(navController = navController, startDestination = Screen.LOGIN.label) {
        composable(Screen.LOGIN.label) {
            LoginScreen(navigator)
        }
        composable(Screen.LIST.label) {
            ListScreen(navigator)
        }
    }
}
