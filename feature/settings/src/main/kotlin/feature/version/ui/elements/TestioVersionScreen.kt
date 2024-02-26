package feature.version.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import core.ui.theme.CompLocalTheme
import version.ui.elements.VersionScreen

@PreviewLightDark
@Composable
private fun TestioVersionScreenPreview() = CompLocalTheme { TestioVersionScreen(onBack = {}) }

@Composable
fun TestioVersionScreen(onBack: () -> Unit) =
    VersionScreen(onBack, surfers, "🤙", "https://instagram.com/surfinua/")

private val surfers = listOf(
    "🏄‍♀️", "🏄🏻‍♀️", "🏄🏼‍♀️", "🏄🏽‍♀️", "🏄🏾‍♀️", "🏄🏿‍♀️",
    "🏄", "🏄🏻", "🏄🏼", "🏄🏽", "🏄🏾", "🏄🏿",
    "🏄‍♂️", "🏄🏻‍♂️", "🏄🏼‍♂️", "🏄🏽‍♂️", "🏄🏾‍♂️", "🏄🏿‍♂️"
)
    .let { buildList { while (size < 199) addAll(it) } }
    .shuffled()
    .flatMap { listOf(it, "🌊") }
    .joinToString(" ")
