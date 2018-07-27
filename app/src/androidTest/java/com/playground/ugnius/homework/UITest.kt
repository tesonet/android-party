package com.playground.ugnius.homework

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.playground.ugnius.homework.views.activites.MainActivity
import com.playground.ugnius.homework.views.fragments.LoginFragment
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class UITest {

    private companion object {
        const val SUCCESSFUL_TEST_USERNAME = "tesonet"
        const val SUCCESSFUL_TEST_PASSWORD = "partyanimal"
        const val UNSUCCESSFUL_TEST_USERNAME = "error"
        const val UNSUCCESSFUL_TEST_PASSWORD = "error"
    }

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before()
    fun restoreState() {
        IdlingRegistry.getInstance().register(activityRule.activity.idlingResource)
        activityRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, LoginFragment())
            .commit()
    }

    @Test
    fun testApplicationFlow() {
        val context = InstrumentationRegistry.getTargetContext()
        onView(withId(R.id.usernameInput)).perform(typeText(SUCCESSFUL_TEST_USERNAME), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText(SUCCESSFUL_TEST_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
        onView(withId(R.id.serversRecycler)).check(matches(isDisplayed()))
        onView(withId(R.id.logout)).perform(click())
        onView(withId(R.id.usernameInput)).perform(typeText(UNSUCCESSFUL_TEST_USERNAME), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText(UNSUCCESSFUL_TEST_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
        try {
            //if animations are turned on
            onView(withText(context.getString(R.string.invalid_credentials))).check(matches(isDisplayed()))
        } catch (ex: NoMatchingViewException) {
            //if animations are turned off
            onView(withId(R.id.serversRecycler)).check(doesNotExist())
        }
    }
}
