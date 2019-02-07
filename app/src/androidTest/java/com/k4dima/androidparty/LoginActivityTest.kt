package com.k4dima.androidparty

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.truth.content.IntentSubject
import androidx.test.runner.AndroidJUnit4
import com.k4dima.androidparty.features.login.ui.LoginActivity
import com.k4dima.androidparty.features.main.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)
    @Test
    fun successfulLogin() {
        // GIVEN
        /*val scenario =
                ActivityScenario.launch(LoginActivity::class.java)*/
        // WHEN
        onView(withId(R.id.user_name)).perform(typeText("tesonet"))
        //intended(hasComponent(LoginActivity::class.java.name))
        onView(withId(R.id.password))
                .perform(typeText("partyanimal"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        //Espresso.closeSoftKeyboard()
        // THEN
        IntentSubject.assertThat(Intents.getIntents().first())
                .hasComponentClass(MainActivity::class.java)
        /*intending(hasComponent(hasShortClassName(".CreateNewSessionActivity")))
                .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))*/
    }
}