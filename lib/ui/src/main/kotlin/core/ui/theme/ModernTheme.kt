package core.ui.theme

import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.S
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.material.MaterialTheme as Material2Theme

const val DynamicColorsDefault = true

@Composable
fun ModernTheme(
    dark: Boolean = isSystemInDarkTheme(),
    dynamicColors: Boolean = DynamicColorsDefault,
    lightColorScheme: ColorScheme = lightColorScheme(),
    darkColorScheme: ColorScheme = darkColorScheme(),
    lightColors: Colors = lightColors(),
    darkColors: Colors = darkColors(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColors && SDK_INT >= S -> {
            val context = LocalContext.current
            if (dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        dark -> darkColorScheme
        else -> lightColorScheme
    }
    val colors = if (dark) darkColors else lightColors
    EdgeToEdge(dark)
    Material2Theme(colors) { MaterialTheme(colorScheme, content = content) }
}

@Composable
private fun EdgeToEdge(dark: Boolean) {
    if (!LocalInspectionMode.current) {
        val activity = LocalContext.current as ComponentActivity
        SideEffect {
            activity.enableEdgeToEdge(
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { dark },
                SystemBarStyle.auto(
                    Color.argb(0xe6, 0xFF, 0xFF, 0xFF),
                    Color.argb(0x80, 0x1b, 0x1b, 0x1b)
                ) { dark },
            )
        }
    }
}