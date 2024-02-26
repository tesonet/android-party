package test.app

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.github.k4dima.testio.BuildConfig
import feature.main.ui.elements.TAG_SERVERS
import feature.main.ui.elements.TAG_SIGN_IN_OUT
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import signin.ui.elements.TAG_PASSWORD
import signin.ui.elements.TAG_USERNAME
import ui.elements.TestioApplication
import java.util.concurrent.TimeUnit.SECONDS
import com.github.k4dima.testio.lib.signin.R as SigninR

@RunWith(AndroidJUnit4::class)
class WalkThroughE2eTest {
    private val context = ApplicationProvider.getApplicationContext<TestioApplication>()
    private val device =
        InstrumentationRegistry.getInstrumentation().let { UiDevice.getInstance(it) }

    @Before
    fun setUp() {
        // Given
        context.packageManager
            .getLaunchIntentForPackage(BuildConfig.APPLICATION_ID)
            .let { context.startActivity(it) }
    }

    @Test
    fun loginWithUsernamePassword() {
        // When
        tag(TAG_SIGN_IN_OUT, true)!!.click()
        text(CONTINUE, true)?.let { device.pressBack() }
        tag(TAG_USERNAME, true)!!.text = "tesonet"
        tag(TAG_PASSWORD)!!.text = "partyanimal"
        text(context.getString(SigninR.string.sign_in))!!.click()
        text(CONTINUE, true)?.let { device.pressBack() }
        // Then
        assertTrue(tag(TAG_SERVERS, true)!!.childCount > 1)
    }

    @Test
    fun loginWithCredentialsManager() {
        // When
        tag(TAG_SIGN_IN_OUT, true)!!.click()
        text(CONTINUE, true)!!.click()
        // Then
        assertTrue(tag(TAG_SERVERS, true)!!.childCount > 1)
    }

    private fun tag(res: String, wait: Boolean = false) = find(By.res(res), wait)
    private fun text(text: String, wait: Boolean = false) = find(By.text(text), wait)

    private fun find(selector: BySelector, wait: Boolean = false) =
        if (wait) device.wait(Until.findObject(selector), SECONDS.toMillis(4))
        else device.findObject(selector)

    companion object {
        private const val CONTINUE = "Continue"
    }
}