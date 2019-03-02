package com.k4dima.androidparty

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By.res
import androidx.test.uiautomator.By.text
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until.findObject
import com.k4dima.androidparty.BuildConfig.APPLICATION_ID
import com.k4dima.androidparty.R.string.*
import com.k4dima.party.app.ui.PartyApp
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit.SECONDS

@RunWith(AndroidJUnit4::class)
class WalkthroughE2ETest {
    @Test
    fun testWalkthrough() {
        // Given
        val context = ApplicationProvider.getApplicationContext<PartyApp>()
                .apply { startActivity(packageManager.getLaunchIntentForPackage(APPLICATION_ID)) }
        // When
        val element: (BySelector) -> UiObject2? = {
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                    .wait(findObject(it), SECONDS.toMillis(1) / 2)
        }
        val elementByRes: (String) -> UiObject2? = { element(res(APPLICATION_ID, it)) }
        val elementByText: (String) -> UiObject2? = { element(text(it)) }
        val string: (Int) -> String = { context.getString(it) }
        elementByRes("logout")?.click()
        elementByText(string(username))!!.text = "tesonet"
        elementByText(string(password))!!.text = "partyanimal"
        elementByText(string(log_in).toUpperCase())!!.click()
        // Then
        assertTrue(elementByRes("servers")!!.childCount > 0)
    }
}