package test.login

import android.app.Activity
import android.content.Context
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import core.ui.theme.ModernTheme
import feature.login.ui.elements.LightSwitch
import feature.login.ui.elements.Title
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import signin.ui.elements.SignInScreen
import signin.ui.state.LoginViewModel.UiState
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS
import com.github.k4dima.testio.lib.signin.R as LibSigninR

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    // Given
    @get:Rule
    val rule = createComposeRule()

    @Test
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
    fun enterCredentials_successfulLogin() {
        // When
        val context = ApplicationProvider.getApplicationContext<Context>()
        val latch = CountDownLatch(1)
        val flow = MutableStateFlow(UiState("", "", false, null))
        rule.setContent {
            ModernTheme {
                val uiState by flow.collectAsState()
                val activity = LocalContext.current as Activity
                val windowSizeClass = calculateWindowSizeClass(activity)
                SignInScreen(
                    {},
                    { Title() },
                    uiState,
                    { username -> flow.update { it.copy(username = username) } },
                    { password -> flow.update { it.copy(password = password) } },
                    { latch.countDown() },
                    windowSizeClass,
                    { LightSwitch(it) }
                )
            }
        }
        val tesonet = "tesonet"
        val partyanimal = "partyanimal"
        rule.onNodeWithText(context.getString(LibSigninR.string.username)).performTextInput(tesonet)
        rule.onNodeWithText(context.getString(LibSigninR.string.password))
            .performTextInput(partyanimal)
        rule.onNodeWithText(context.getString(LibSigninR.string.password)).performImeAction()
        // Then
        assertTrue(latch.await(4, SECONDS))
        assertEquals(UiState(tesonet, partyanimal, false, null), flow.value)
    }
}