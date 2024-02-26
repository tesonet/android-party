package ui.elements

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import core.ui.theme.CompLocalTheme
import feature.login.ui.elements.navigateToSignIn
import feature.login.ui.elements.signInScreen
import feature.main.ui.elements.SERVERS_ROUTE
import feature.main.ui.elements.serversScreen
import feature.settings.ui.elements.navigateToSettings
import feature.settings.ui.elements.settingsScreen
import feature.version.ui.elements.navigateToVersion
import feature.version.ui.elements.versionScreen

@PreviewLightDark
@Composable
private fun TestioAppPreview() = CompLocalTheme { TestioApp(Intent()) }

@Composable
fun TestioApp(intent: Intent) {
    val navController = rememberNavController()
    TestioNavHost(navController)
    LaunchedEffect(Unit) {
        if (intent.action != Intent.ACTION_APPLICATION_PREFERENCES) return@LaunchedEffect
        intent.action = null
        navController.navigateToSettings()
    }
}

@Composable
private fun TestioNavHost(navController: NavHostController) =
    NavHost(navController, SERVERS_ROUTE) {
        serversScreen(navController::navigateToSignIn)
        signInScreen(navController::popBackStack)
        settingsScreen(navController::popBackStack, navController::navigateToVersion)
        versionScreen(navController::popBackStack)
    }