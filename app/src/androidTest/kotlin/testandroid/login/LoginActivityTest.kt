package testandroid.login

import android.os.Build
import android.view.autofill.AutofillManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.data.di.PersistenceModule
import app.domain.AppPreferenceRepository
import app.ui.Party
import com.k4dima.party.R
import login.ui.LoginActivity
import main.ui.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    // Given
    @get:Rule
    val activityScenarioRule = activityScenarioRule<LoginActivity>()

    @get:Rule
    val intentsRule = IntentsRule()
    private val app = ApplicationProvider.getApplicationContext<Party>()
    private val preferences = PersistenceModule.preferences(app)

    @Before
    fun setUp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            app.getSystemService(AutofillManager::class.java).disableAutofillServices()
        AppPreferenceRepository { preferences }.token = null
    }

    @Test
    fun successfulLogin() {
        // When
        val latch = CountDownLatch(2)
        activityScenarioRule.scenario.onActivity {
            it.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) = latch.countDown()
            })
        }
        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            val success = (key == AppPreferenceRepository.TOKEN_KEY) &&
                    sharedPreferences.getString(key, null)!!.isNotEmpty()
            if (success) latch.countDown()
        }
        onView(withId(R.id.user_name)).perform(typeText("tesonet"))
        onView(withId(R.id.password))
            .perform(typeText("partyanimal"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        // Then
        latch.await(8, TimeUnit.SECONDS).let { Assert.assertTrue(it) }
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.qualifiedName))
    }
}