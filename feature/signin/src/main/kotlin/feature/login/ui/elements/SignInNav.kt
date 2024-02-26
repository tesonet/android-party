package feature.login.ui.elements

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import core.ui.elements.composableAnimated

private const val SIGN_IN_ROUTE = "sign_in"

fun NavGraphBuilder.signInScreen(onBack: () -> Unit) =
    composableAnimated(SIGN_IN_ROUTE) { LoginScreen(onBack) }

fun NavController.navigateToSignIn() = navigate(SIGN_IN_ROUTE)