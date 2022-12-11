package test.app

import android.view.autofill.AutofillManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import app.ui.Party
import com.k4dima.party.BuildConfig
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WalkthroughE2ETest {
    @Before
    fun setUp() {
        InstrumentationRegistry.getInstrumentation()
            .targetContext
            .getSystemService(AutofillManager::class.java)
            .disableAutofillServices()
    }

    @Test
    fun testWalkthrough() {
        // Given
        val context = ApplicationProvider.getApplicationContext<Party>()
        context.packageManager
            .getLaunchIntentForPackage(BuildConfig.APPLICATION_ID)
            .let { context.startActivity(it) }

        fun uiObjectByRes(res: String): UiObject2? {
            val device = InstrumentationRegistry.getInstrumentation()
                .let { UiDevice.getInstance(it) }
            val selector = By.res(BuildConfig.APPLICATION_ID, res)
            val condition = Until.findObject(selector)
            val timeout = TimeUnit.SECONDS.toMillis(2)
            return device.wait(condition, timeout)
        }
        uiObjectByRes("logout")?.click()
        uiObjectByRes("user_name")!!.text = "tesonet"
        uiObjectByRes("password")!!.text = "partyanimal"
        uiObjectByRes("login")!!.click()
        // Then
        (uiObjectByRes("servers")!!.childCount > 0).let { Assert.assertTrue(it) }
    }
}