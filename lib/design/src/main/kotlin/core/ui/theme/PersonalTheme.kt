package core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import settings.ui.state.LocalDarkTheme
import settings.ui.state.LocalPersonalColors
import settings.ui.state.SettingsCompositionLocal

@Composable
fun PersonalTheme(
    lightColorScheme: ColorScheme = lightColorScheme(),
    darkColorScheme: ColorScheme = darkColorScheme(),
    lightColors: Colors = lightColors(),
    darkColors: Colors = darkColors(),
    content: @Composable () -> Unit
) = SettingsCompositionLocal {
    ModernTheme(
        LocalDarkTheme.current,
        LocalPersonalColors.current,
        lightColorScheme,
        darkColorScheme,
        lightColors,
        darkColors,
        content
    )
}