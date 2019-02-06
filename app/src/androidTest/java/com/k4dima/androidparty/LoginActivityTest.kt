package com.k4dima.androidparty

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.Intents.getIntents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.truth.content.IntentSubject
import androidx.test.runner.AndroidJUnit4
import com.k4dima.androidparty.features.login.ui.LoginActivity
import com.k4dima.androidparty.features.main.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Test
    fun successfulLogin() {
        // GIVEN
        val scenario =
                ActivityScenario.launch(LoginActivity::class.java)
        // WHEN
        onView(withId(R.id.user_name)).perform(typeText("test_user"))
        onView(withId(R.id.password))
                .perform(typeText("correct_password"))
        onView(withId(R.id.login)).perform(click())
        // THEN
        IntentSubject.assertThat(getIntents().first())
                .hasComponentClass(MainActivity::class.java)
    }
}