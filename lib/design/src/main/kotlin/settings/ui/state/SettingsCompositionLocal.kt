package settings.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import core.ui.theme.DynamicColorsDefault

private const val DarkThemeDefault = false
val LocalDarkThemeState = staticCompositionLocalOf { mutableStateOf(DarkThemeDefault) }
val LocalPersonalColorsState = staticCompositionLocalOf { mutableStateOf(DynamicColorsDefault) }
val LocalDarkTheme = compositionLocalOf { DarkThemeDefault }
val LocalPersonalColors = compositionLocalOf { DynamicColorsDefault }

@Composable
fun SettingsCompositionLocal(
    personalColorsDefault: Boolean = DynamicColorsDefault, content: @Composable () -> Unit
) {
    val darkThemeState = darkThemeSettingState()
    val personalColorsState = personalColorsSettingState(personalColorsDefault)
    val darkTheme by darkThemeState
    val personalColors by personalColorsState
    CompositionLocalProvider(
        LocalDarkThemeState provides darkThemeState,
        LocalPersonalColorsState provides personalColorsState,
        LocalDarkTheme provides darkTheme,
        LocalPersonalColors provides personalColors,
        content = content
    )
}