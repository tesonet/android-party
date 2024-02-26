package core.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import settings.ui.state.LocalDarkTheme
import settings.ui.state.LocalPersonalColors
import settings.ui.state.SettingsCompositionLocal

private val LightColorScheme = lightColorScheme(
    md_theme_light_primary,
    md_theme_light_onPrimary,
    md_theme_light_primaryContainer,
    md_theme_light_onPrimaryContainer,
    md_theme_light_inversePrimary,
    md_theme_light_secondary,
    md_theme_light_onSecondary,
    md_theme_light_secondaryContainer,
    md_theme_light_onSecondaryContainer,
    background = ColorLightTokens.Background,
    surface = ColorLightTokens.Surface,
    surfaceVariant = ColorLightTokens.SurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    md_theme_dark_primary,
    md_theme_dark_onPrimary,
    md_theme_dark_primaryContainer,
    md_theme_dark_onPrimaryContainer,
    md_theme_dark_inversePrimary,
    md_theme_dark_secondary,
    md_theme_dark_onSecondary,
    md_theme_dark_secondaryContainer,
    md_theme_dark_onSecondaryContainer
)

private val LightColors = lightColors(
    md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = ColorLightTokens.Background,
    surface = ColorLightTokens.Surface,
    onPrimary = md_theme_light_onPrimary,
    onSecondary = md_theme_light_onSecondary,
)

private val DarkColors = darkColors(
    md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
)

@Composable
fun TestioTheme(
    disableDynamicColors: Boolean = !DynamicColorsDefault, content: @Composable () -> Unit
) {
    val darkTheme = LocalDarkTheme.current
    val dynamicColors = !disableDynamicColors && LocalPersonalColors.current
    ModernTheme(
        darkTheme, dynamicColors, LightColorScheme, DarkColorScheme, LightColors, DarkColors,
        content
    )
}

@Composable
fun logoSecondary() =
    if (LocalDarkTheme.current) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.secondaryContainer

@Composable
fun logoPrimary() =
    if (LocalDarkTheme.current) Color.White
    else MaterialTheme.colorScheme.primary

@Composable
fun CompLocalTheme(
    disableDynamicColors: Boolean = !DynamicColorsDefault, content: @Composable () -> Unit
) = SettingsCompositionLocal { TestioTheme(disableDynamicColors, content = content) }