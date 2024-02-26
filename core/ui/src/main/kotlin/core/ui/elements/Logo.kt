package core.ui.elements

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
//noinspection MissingResourceImportAlias
import com.github.k4dima.testio.core.ui.R
import core.ui.theme.CompLocalTheme
import core.ui.theme.logoPrimary
import core.ui.theme.logoSecondary

@PreviewLightDark
@Composable
private fun LogoPreview() = CompLocalTheme(true) { Surface { Logo(typography.displayLarge) } }

@Composable
fun Logo(style: TextStyle) {
    val annotatedString = buildAnnotatedString {
        val appName = stringResource(R.string.app_name)
        val testio = appName.substring(0..<appName.length - 1)
        val dot = appName[appName.length - 1]
        withStyle(SpanStyle(logoPrimary())) { append(testio) }
        withStyle(SpanStyle(logoSecondary())) { append(dot) }
    }
    Text(annotatedString, fontWeight = Bold, style = style)
}