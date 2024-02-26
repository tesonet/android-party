package feature.main.ui.elements

import androidx.navigation.NavGraphBuilder
import core.ui.elements.composableAnimated

const val SERVERS_ROUTE = "servers"

fun NavGraphBuilder.serversScreen(onSignIn: () -> Unit) =
    composableAnimated(SERVERS_ROUTE) { MainScreenWithPreview(onSignIn) }