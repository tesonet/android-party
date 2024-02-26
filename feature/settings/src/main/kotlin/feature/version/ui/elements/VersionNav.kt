package feature.version.ui.elements

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.ui.elements.composableAnimated

private const val VERSION_ROUTE = "version"

fun NavGraphBuilder.versionScreen(onBack: () -> Unit) =
    composableAnimated(VERSION_ROUTE) { TestioVersionScreen(onBack) }

fun NavController.navigateToVersion() = navigate(VERSION_ROUTE)