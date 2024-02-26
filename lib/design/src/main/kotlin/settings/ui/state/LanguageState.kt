package settings.ui.state

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.alorma.compose.settings.storage.base.SettingValueState
import com.github.k4dima.testio.lib.design.R
import java.util.Locale

class LanguageState(context: Context, supportedCodes: List<String>) : SettingValueState<Int> {
    private val codeApp = AppCompatDelegate.getApplicationLocales()[0]
        ?.toLanguageTag()
        ?.split("-")
        ?.get(0)
        ?: EMPTY
    private val codeToDisplay = supportedCodes
        .associateWith { Locale(it, EMPTY) }
        .mapValues { (_, locale) -> locale.getDisplayLanguage(locale).capitalize(locale) }
        .let { it + (EMPTY to context.getString(R.string.default_)) }
        .toSortedMap()
    val displays: List<String> = codeToDisplay.values.toList()
    private val codes = codeToDisplay.keys.toList()
    override var value: Int
        get() = codes.indexOf(codeApp)
        set(value) = codes[value]
            .let { LocaleListCompat.forLanguageTags(it) }
            .let { AppCompatDelegate.setApplicationLocales(it) }

    override fun reset() = Unit
    private fun String.capitalize(locale: Locale) =
        this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

    companion object {
        private const val EMPTY = ""
    }
}