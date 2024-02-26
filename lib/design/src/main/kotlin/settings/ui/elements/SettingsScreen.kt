package settings.ui.elements

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.ui.SettingsList
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import core.ui.elements.navigationIcon
import core.ui.theme.PersonalTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import settings.domain.logic.DarkTheme
import settings.ui.state.DarkThemeSettingValueState
import settings.ui.state.LanguageState
import settings.ui.state.LocalDarkThemeState
import settings.ui.state.LocalPersonalColorsState
import settings.ui.state.SettingState
import com.github.k4dima.testio.lib.design.R as LibDesignR

@PreviewLightDark
@Composable
private fun SettingsPreview() =
    PersonalTheme { SettingsScreen({}, null, persistentListOf("en", "ru", "ua")) }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit, onVersion: (() -> Unit)? = null, locales: ImmutableList<String>? = null
) {
    val scrollBehavior = exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SettingsTopAppBar(scrollBehavior, onBack) },
        content = { SettingsContent(it, onVersion, locales) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, onBack: () -> Unit) =
    LargeTopAppBar(
        {
            Text(
                stringResource(LibDesignR.string.settings),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = navigationIcon(onBack),
        scrollBehavior = scrollBehavior
    )

@Composable
private fun SettingsContent(
    paddingValues: PaddingValues,
    onVersion: (() -> Unit)?,
    locales: ImmutableList<String>?
) {
    val color = MaterialTheme.colorScheme.primary
    val labelFontSize = 14.sp
    val settingFontSize = 20.sp
    val context = LocalContext.current
    val modifier = Modifier.height(84.dp) // alorma settings fix
    LazyColumn(
        contentPadding = PaddingValues(
            paddingValues.calculateLeftPadding(LocalLayoutDirection.current),
            paddingValues.calculateTopPadding(),
            paddingValues.calculateEndPadding(LocalLayoutDirection.current)
        )
    ) {
        item {
            ListItem({
                Text(
                    stringResource(LibDesignR.string.display), color = color,
                    fontSize = labelFontSize
                )
            })
        }
        item {
            val darkThemeBoolState = (LocalDarkThemeState.current as SettingState).settingValueState
            val darkThemeIntState by (darkThemeBoolState as DarkThemeSettingValueState).darkThemeIntState
            SettingsSwitch(
                modifier,
                state = darkThemeBoolState,
                title = {
                    Text(stringResource(LibDesignR.string.dark_theme), fontSize = settingFontSize)
                },
                subtitle = {
                    val darkTheme = DarkTheme.entriesCompat[darkThemeIntState]
                    Text(stringResource(darkTheme.id))
                }
            )
        }
        if (SDK_INT >= Build.VERSION_CODES.S) item {
            val personalColorsState =
                (LocalPersonalColorsState.current as SettingState).settingValueState
            SettingsSwitch(
                modifier,
                state = personalColorsState,
                title = {
                    Text(
                        stringResource(LibDesignR.string.personal_colors),
                        fontSize = settingFontSize
                    )
                })
        }
        if (locales != null) item {
            val languageState = remember { LanguageState(context, locales) }
            SettingsList(
                state = languageState,
                title = {
                    Text(
                        stringResource(LibDesignR.string.language), fontSize = settingFontSize
                    )
                },
                items = languageState.displays
            )
        }
        item {
            ListItem({
                Text(
                    stringResource(LibDesignR.string.about), color = color, fontSize = labelFontSize
                )
            })
        }
        item {
            val packageName = remember { context.packageName }
            val uriHandler = LocalUriHandler.current
            SettingsMenuLink(
                title = {
                    Text(
                        stringResource(LibDesignR.string.view_in_play_store),
                        fontSize = settingFontSize
                    )
                },
                subtitle = { Text(stringResource(LibDesignR.string.rate_and_review)) },
                onClick = {
                    val uri =
                        "https://play.google.com/store/apps/details?id=$packageName"
                    uriHandler.openUri(uri)
                })
        }
        item {
            val versionName = context.versionName()
            val title: @Composable () -> Unit =
                { Text(stringResource(LibDesignR.string.version), fontSize = settingFontSize) }
            val subtitle: @Composable () -> Unit = { Text(versionName) }
            if (onVersion == null) ListItem(title, supportingContent = subtitle)
            else SettingsMenuLink(title = title, subtitle = subtitle, onClick = onVersion)
        }
    }
}

@Composable
fun Context.versionName(): String =
    if (LocalInspectionMode.current) "0.1.0"
    else packageManager
        .let {
            val packageName = packageName
            if (SDK_INT >= TIRAMISU)
                it.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            else it.getPackageInfo(packageName, 0)
        }
        .versionName