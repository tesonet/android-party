package settings.ui.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.base.rememberIntSettingState
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.datastore.rememberPreferenceDataStoreBooleanSettingState
import com.alorma.compose.settings.storage.datastore.rememberPreferenceDataStoreIntSettingState
import settings.domain.logic.DarkTheme

@Composable
internal fun darkThemeSettingValueState(): SettingValueState<Boolean> {
    val systemInDarkTheme = isSystemInDarkTheme()
    val darkThemeState = darkThemeIntSettingValueState()
    return remember { DarkThemeSettingValueState(darkThemeState, systemInDarkTheme) }
}

@Composable
internal fun personalColorsSettingValueState(default: Boolean = true) =
    if (LocalInspectionMode.current) rememberBooleanSettingState(default)
    else rememberPreferenceDataStoreBooleanSettingState(
        key = "pref_theme_personal", defaultValue = default
    )

@Composable
internal fun darkThemeIntSettingValueState() =
    if (LocalInspectionMode.current) rememberIntSettingState(DarkTheme.defaultIndex)
    else rememberPreferenceDataStoreIntSettingState(
        key = "pref_theme_dark", defaultValue = DarkTheme.defaultIndex
    )

@Composable
internal fun darkThemeSettingState(): MutableState<Boolean> =
    SettingState(darkThemeSettingValueState())

@Composable
internal fun personalColorsSettingState(default: Boolean = true): MutableState<Boolean> =
    SettingState(personalColorsSettingValueState(default))

internal class DarkThemeSettingValueState(
    internal val darkThemeIntState: SettingValueState<Int>,
    private val systemInDarkTheme: Boolean
) : SettingValueState<Boolean> {
    private var darkThemeInt by darkThemeIntState
    override var value: Boolean
        get() =
            if (darkThemeInt == DarkTheme.defaultIndex) systemInDarkTheme
            else darkThemeInt == DarkTheme.index(DarkTheme.On)
        set(value) {
            darkThemeInt =
                if (value == systemInDarkTheme) DarkTheme.defaultIndex
                else DarkTheme.index(if (value) DarkTheme.On else DarkTheme.Off)
        }

    override fun reset() {
        value = systemInDarkTheme
    }
}

internal class SettingState<T>(internal val settingValueState: SettingValueState<T>) :
    MutableState<T> {
    override var value by settingValueState
    override fun component1() = value
    override fun component2(): (T) -> Unit = { value = it }
}