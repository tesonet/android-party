package com.k4dima.androidparty.features.main.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.k4dima.androidparty.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainTest {
    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testMain() {
        println("************************")
        println("************************")
        val view = onView(withText(R.string.log_in))
        //view.check()
        println(view)
        view.perform(click())
        //AppPreferenceRepository(InstrumentationRegistry.getContext())
        println("************************")
        println("************************")
    }
}