package com.teso.net.ui.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.teso.net.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFlowTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainFlowTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val appCompatEditText = onView(
                allOf(withId(R.id.logoEdit),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginName),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("tesonet"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.logoEdit), withText("tesonet"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.loginName), withText("tesonet")),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText2.perform(pressImeActionButton())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.logoEdit),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginPassword),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText3.perform(replaceText("partyanimal"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
                allOf(withId(R.id.logoEdit), withText("partyanimal"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.loginPassword), withText("partyanimal")),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText4.perform(pressImeActionButton())

        val appCompatTextView = onView(
                allOf(withId(R.id.loginButton), withText("Log In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainer),
                                        0),
                                4),
                        isDisplayed()))
        appCompatTextView.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.serverList),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                6)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val recyclerView2 = onView(
                allOf(withId(R.id.serverList),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                6)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
