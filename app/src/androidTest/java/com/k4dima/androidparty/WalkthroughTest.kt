package com.k4dima.androidparty

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.SearchCondition
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until.findObject
import androidx.test.uiautomator.Until.hasObject
import com.k4dima.androidparty.BuildConfig.APPLICATION_ID
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalkthroughTest {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context = InstrumentationRegistry.getTargetContext()

    @Before
    fun startMainActivity() {
        val intent = context.packageManager.getLaunchIntentForPackage(APPLICATION_ID)
        context.startActivity(intent)
        wait1(hasObject(By.pkg(APPLICATION_ID).depth(0)))
    }

    @Test
    fun testWalkthrough() {
        var login = loginButton()
        if (login != null) {
            login(login)
        } else {
            val logout = waitFindByRes("logout")
            logout.click()
            login = loginButton()
            login(login!!)
        }
    }

    private fun login(login: UiObject2) {
        login.click()
        val servers = waitFindByRes("servers")
        assertTrue(servers.childCount > 0)
    }

    private fun loginButton(): UiObject2? =
            wait1(findObject(By.text(context.getString(R.string.log_in).toUpperCase())))

    private fun waitFindByRes(res: String) = wait1(findObject(By.res(APPLICATION_ID, res)))!!

    private fun <R> wait1(condition: SearchCondition<R>): R =
            device.wait(condition, 500)
}