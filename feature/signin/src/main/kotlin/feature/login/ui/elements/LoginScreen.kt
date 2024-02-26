package feature.login.ui.elements

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import core.ui.elements.Logo
import core.ui.theme.CompLocalTheme
import core.ui.theme.TestioTheme
import kotlinx.collections.immutable.persistentListOf
import settings.ui.state.LocalDarkTheme
import settings.ui.state.LocalDarkThemeState
import signin.ui.elements.PreviewPixel5Landscape
import signin.ui.elements.SignInScreen
import signin.ui.elements.pixel5Size
import signin.ui.state.LoginViewModel
import com.github.k4dima.testio.feature.signin.R as FeatureSigninR

@OptIn(ExperimentalComposeUiApi::class)
@PreviewLightDark
@PreviewPixel5Landscape
@Composable
private fun LoginScreenPreview() =
    CompLocalTheme(true) {
        val uiState = LoginViewModel.UiState("", "", false, null)
        val background: @Composable (() -> Unit)? =
            if (LocalDarkTheme.current) { -> Background() } else null
        SignInScreen(
            {}, { Title() }, uiState, {}, {}, {}, pixel5Size, { LightSwitch(it) }, background
        )
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(onBack: () -> Unit) =
    TestioTheme(true) {
        SignInScreen(
            onBack,
            { Title() },
            { LightSwitch(it) },
            if (LocalDarkTheme.current) { -> Background() } else null,
            persistentListOf(AutofillType.Username),
            true
        )
    }

@VisibleForTesting
@Composable
fun Title() = Logo(MaterialTheme.typography.displayLarge)

@VisibleForTesting
@Composable
fun LightSwitch(modifier: Modifier) {
    var darkTheme by LocalDarkThemeState.current
    Switch(
        !darkTheme,
        { darkTheme = !it },
        modifier,
        {
            Icon(
                if (darkTheme) Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
                stringResource(FeatureSigninR.string.light_switch)
            )
        }
    )
}

@VisibleForTesting
@Composable
fun Background() = Image(
    painterResource(FeatureSigninR.drawable.bg),
    stringResource(FeatureSigninR.string.surfing),
    Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop
)