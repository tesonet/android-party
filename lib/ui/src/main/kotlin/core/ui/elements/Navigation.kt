package core.ui.elements

//noinspection MissingResourceImportAlias
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.k4dima.testio.lib.ui.R
import core.ui.theme.ModernTheme

fun NavGraphBuilder.composableAnimated(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    val left = AnimatedContentTransitionScope.SlideDirection.Left
    val right = AnimatedContentTransitionScope.SlideDirection.Right
    val spec = tween<IntOffset>()
    composable(
        route,
        enterTransition = { slideIntoContainer(left, spec) },
        exitTransition = { slideOutOfContainer(left, spec) },
        popEnterTransition = { slideIntoContainer(right, spec) },
        popExitTransition = { slideOutOfContainer(right, spec) },
        content = content
    )
}

fun navigationIcon(onBack: () -> Unit): @Composable () -> Unit =
    {
        IconButton(onBack) {
            Icon(Icons.AutoMirrored.Rounded.ArrowBack, stringResource(R.string.navigate_back))
        }
    }

@PreviewLightDark
@Composable
private fun NavigationIconPreview() = ModernTheme { Surface { navigationIcon(onBack = {})() } }