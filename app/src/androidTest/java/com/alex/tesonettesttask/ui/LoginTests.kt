package com.alex.tesonettesttask.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.utils.Preferences
import com.alex.tesonettesttask.ui.activities.MainActivity
import com.alex.tesonettesttask.utils.waitFor
import io.realm.Realm
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginTests {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setup() {
        Realm.getDefaultInstance().executeTransaction { it.deleteAll() }
        Preferences.removeToken()
        activityRule.launchActivity(null)
    }

    @Test
    fun testSuccessLoginAndLogout() {
        onView(ViewMatchers.withId(R.id.usernameEditText)).perform(ViewActions.typeText(TEST_USERNAME))
        onView(ViewMatchers.withId(R.id.passwordEditText)).perform(ViewActions.typeText(TEST_PASSWORD))
        onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click())
        // verify that list shows up and has items
        onView(ViewMatchers.withId(R.id.serverList)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityRule.activity.runOnUiThread { assert(activityRule.activity.findViewById<RecyclerView>(R.id.serverList).adapter!!.itemCount > 0) }
        // perform logout and make sure we are back at the login screen and data is cleared
        onView(ViewMatchers.withId(R.id.logoutButton)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.loginButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        assert(Realm.getDefaultInstance().where(Server::class.java).count() == 0L)
        assert(Preferences.getAuthToken().isEmpty())
    }

    @Test
    fun testFailLogin() {
        // make sure login error shows and hides in time
        onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click())
        assert(activityRule.activity.findViewById<TextView>(R.id.errorText).alpha == 1f)
        onView(isRoot()).perform(waitFor(3500))
        assert(activityRule.activity.findViewById<TextView>(R.id.errorText).alpha == 0f)
    }

    companion object {
        private const val TEST_USERNAME = "tesonet"
        private const val TEST_PASSWORD = "partyanimal"
    }
}
