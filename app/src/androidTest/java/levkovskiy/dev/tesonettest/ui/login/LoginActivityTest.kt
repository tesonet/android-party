package levkovskiy.dev.tesonettest.ui.login

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.mvp.presenter.MainPresenter
import levkovskiy.dev.tesonettest.ui.loading.LoadingActivity
import levkovskiy.dev.tesonettest.utils.PreferenceHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
  private var email = "tesonet"
  private var pass = "partyanimal"

  @get:Rule @JvmField
  var activityTestRule = ActivityTestRule(LoginActivity::class.java)
  var mainPresenter: MainPresenter = mock()
  @Before
  fun setup() {
//    `when`(PreferenceHelper.getStringPreference(getInstrumentation().context, PreferenceHelper.token))
//        .thenReturn(null)
    `when`(PreferenceHelper.isNeedToLogin(getInstrumentation().context)).thenReturn(true)
    activityTestRule.launchActivity(null)

  }

  @Test
  fun loginTest() {

    onView(withId(R.id.et_username)).perform(typeText(email))
    onView(withId(R.id.et_password)).perform(typeText(pass), closeSoftKeyboard())
    onView(withId(R.id.login)).perform(click())

    intended(hasComponent(LoadingActivity::class.java.name))
  }
}
