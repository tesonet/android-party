package version.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults.filledTonalButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.ui.elements.navigationIcon
import core.ui.theme.PersonalTheme
import io.github.z4kn4fein.semver.Version
import settings.ui.elements.versionName

@PreviewLightDark
@Composable
private fun VersionPreview() =
    PersonalTheme { VersionScreen({}, emojiPreview, "😋", "https://github.com/k4dima/") }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VersionScreen(
    onBackClick: () -> Unit, emojiBackground: String, emojiButton: String, url: String
) = Surface {
    Box {
        val initialValue = if (LocalInspectionMode.current) 2 else 0
        var count by rememberSaveable { mutableIntStateOf(initialValue) }
        val background by remember { derivedStateOf { count > 0 } }
        if (background) Text(
            emojiBackground,
            Modifier
                .padding(horizontal = 8.dp)
                .offset(y = (-32).dp),
            fontSize = 48.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 72.sp,
            overflow = TextOverflow.Visible
        )
        Scaffold(
            topBar = {
                TopAppBar(
                    {},
                    navigationIcon = navigationIcon(onBackClick),
                    colors = topAppBarColors(Color.Transparent)
                )
            },
            containerColor = Color.Transparent
        ) {
            Box(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                val uriHandler = LocalUriHandler.current
                FilledTonalButton(
                    { if (++count > 2) uriHandler.openUri(url) },
                    Modifier.align(Alignment.Center),
                    colors = filledTonalButtonColors(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    val version = appVersion()
                    val text by remember { derivedStateOf { if (count > 1) emojiButton else version } }
                    Text(text, fontSize = 120.sp)
                }
            }
        }
    }
}

private val emojiPreview = listOf(
    "🍏", "🍎", "🍐", "🍊", "🍋", "🍌", "🍉", "🍇", "🍓", "🫐", "🍈", "🍒", "🍑", "🥭", "🍍",
    "🥥", "🥝"
)
    .shuffled()
    .let { buildList { while (size < 417) addAll(it) } }
    .joinToString(" ")

@Composable
private fun appVersion() = LocalContext
    .current
    .versionName()
    .let { Version.parse(it) }
    .let { (major, minor, patch) -> if (major == 0) listOf(major, minor, patch) else listOf(major) }
    .joinToString(".")