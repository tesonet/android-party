package com.k4dima.androidparty

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import com.k4dima.party.login.ui.LoginActivity
import com.k4dima.androidparty.features.main.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityLocalTest {
    // GIVEN
    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun successfulLogin() {
        // WHEN
        onView(withId(R.id.user_name)).perform(typeText("tesonet"))
        onView(withId(R.id.password)).perform(typeText("partyanimal"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        // THEN
        IntentSubject.assertThat(Intents.getIntents().first())
                .hasComponentClass(MainActivity::class.java)
    }
}