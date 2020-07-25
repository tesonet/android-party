package gj.tesonet.ui.login

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import gj.tesonet.*
import gj.tesonet.backend.Backend
import gj.tesonet.data.model.User
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @Test
    fun missingInput() {
        val scenario = launchActivity<LoginActivity>()

        onView(withId(R.id.name)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(replaceText(""), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.msg_missing_login_data)).check(matches(isDisplayed()))
        onView(withText(android.R.string.ok)).perform(click())

        onView(withId(R.id.name)).perform(replaceText("tesonet"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(replaceText(""), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.msg_missing_login_data)).check(matches(isDisplayed()))
        onView(withText(android.R.string.ok)).perform(click())

        onView(withId(R.id.name)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(replaceText("partyanimal"), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.msg_missing_login_data)).check(matches(isDisplayed()))
    }

    @Test
    fun wrongCredentials() {
        // alternative option - mocked interceptor,
        // although the latter requires injecting mock into backend code internals
        val server = MockWebServer()
        server.start()
        Backend.url = server.url("/").toString()

        server.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                if (request?.path == "/tokens") {
                    return MockResponse().apply {
                        setResponseCode(401)
                        setBody("{\"message\":\"Unauthorized\"}")
                    }
                } else {
                    return MockResponse().setResponseCode(404)
                }
            }
        })

        val scenario = launchActivity<LoginActivity>()

        onView(withId(R.id.name)).perform(replaceText("any"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(replaceText("any"), closeSoftKeyboard())

        onView(withId(R.id.login)).perform(click())

        waitFor(
            withText(R.string.msg_failed_login),
            matches(isDisplayed())
        )

        server.shutdown()
    }

    @Test
    fun goodCredentials() {
        // alternative option - mocked interceptor,
        // although the latter requires injecting mock into backend code internals
        val server = MockWebServer()
        server.start()
        Backend.url = server.url("/").toString()

        server.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                return when (request?.path) {
                    "/tokens" -> {
                        val user: User = request.body.fromJson()
                        assertEquals("any", user.name)
                        assertEquals("pwd", user.password)

                        MockResponse().apply {
                            setResponseCode(200)
                            setBody("{\"token\":\"123\"}")
                        }
                    }
                    "/servers" -> {
                        assertEquals("Bearer 123", request.getHeader("Authorization"))

                        MockResponse().apply {
                            setResponseCode(200)
                            setBody("[]")
                        }
                    }
                    else -> {
                        MockResponse().setResponseCode(404)
                    }
                }
            }
        })

        lateinit var app: App
        val scenario = launchActivity<LoginActivity>().onActivity { activity ->
            app = activity.application as App
        }

        onView(withId(R.id.name)).perform(
            replaceText("any"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.password)).perform(
            replaceText("pwd"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.login)).perform(click())

        waitFor(withId(R.id.list), matches(isDisplayed()))

        assertEquals("any", app.user?.name)
        assertEquals("pwd", app.user?.password)

        server.shutdown()
    }
}
