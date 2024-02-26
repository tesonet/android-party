package feature.settings.ui.elements

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.ui.elements.composableAnimated

private const val SETTINGS_ROUTE = "settings"

fun NavGraphBuilder.settingsScreen(onBack: () -> Unit, onVersion: () -> Unit) =
    composableAnimated(SETTINGS_ROUTE) { TestioSettingsScreen(onBack, onVersion) }

fun NavController.navigateToSettings() = navigate(SETTINGS_ROUTE)