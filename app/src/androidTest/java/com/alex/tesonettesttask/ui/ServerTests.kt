package com.alex.tesonettesttask.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.utils.Preferences
import com.alex.tesonettesttask.ui.activities.MainActivity
import com.alex.tesonettesttask.utils.atPosition
import io.realm.Realm
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ServerTests {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setup() {
        Realm.getDefaultInstance().executeTransaction { it.deleteAll() }
        Preferences.removeToken()
    }

    @Test
    fun testServerShows() {
        // save test server object to database before launching activity
        val server = Server()
        server.name = TEST_SERVER_NAME
        server.distance = TEST_SERVER_DISTANCE
        Realm.getDefaultInstance().executeTransaction { it.insertOrUpdate(server) }
        activityRule.launchActivity(null)
        // check that server list shows saved item
        onView(withId(R.id.serverList)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.serverList)).check(matches(atPosition(0, hasDescendant(withText(TEST_SERVER_NAME)))))
        onView(withId(R.id.serverList)).check(matches(atPosition(0, hasDescendant(withText(String.format("%s km", TEST_SERVER_DISTANCE))))))
    }
    
    companion object {
        private const val TEST_SERVER_NAME = "server"
        private const val TEST_SERVER_DISTANCE = 100
    }
}
