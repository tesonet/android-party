package feature.settings.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import core.ui.theme.CompLocalTheme
import kotlinx.collections.immutable.persistentListOf
import settings.ui.elements.SettingsScreen

@PreviewLightDark
@Composable
private fun TestioSettingsScreenPreview() = CompLocalTheme { TestioSettingsScreen({}, {}) }

@Composable
fun TestioSettingsScreen(onBack: () -> Unit, onVersion: () -> Unit) =
    SettingsScreen(onBack, onVersion, persistentListOf("en", "ru", "uk"))