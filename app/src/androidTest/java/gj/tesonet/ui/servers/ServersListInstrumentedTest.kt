package gj.tesonet.ui.servers

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import gj.tesonet.*
import gj.tesonet.backend.Backend
import gj.tesonet.data.model.Server
import gj.tesonet.ui.login.LoginActivity
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ServersListInstrumentedTest {

    @Test
    fun remoteEmpty() {
        mockServers(emptyList())

        val scenario = launchActivity<ServerListActivity>()

        waitFor(withId(R.id.list), matches(isDisplayed()))

        onView(withId(R.id.list)).check(matches(hasChildCount(0)))
    }

    @Test
    fun remoteLoad() {
        val servers = listOf(Server(0, "one #11", 12), Server(0, "two #22", 34))
        mockServers(servers)

        val countDownLatch = CountDownLatch(1)
        lateinit var app: App
        val scenario = launchActivity<LoginActivity>().onActivity { activity ->
            app = activity.application as App

            runBlocking {
                app.data.servers().setAll(listOf(Server(0, "dummy", 45)))
            }

            app.online = true
            countDownLatch.countDown()
        }

        // unconfined dispatcher would allow to avoid such synchronization
        // although code must be altered to avoid direct Dispatchers.IO or any usage
        countDownLatch.await(5, TimeUnit.SECONDS)

        onView(withId(R.id.name)).perform(closeSoftKeyboard())
        onView(withId(R.id.login)).perform(ViewActions.click())

        waitFor(withId(R.id.list), matches(isDisplayed()))

        onView(withId(R.id.list)).check(matches(hasChildCount(2)))

        repeatFor {
            runBlocking {
                app.data.servers().count()
            } > 0
        }

        val list = runBlocking {
            app.data.servers().getAll()
        }

        assertListEquals(servers, list) { a, b ->
            a.name == b.name && a.distance == b.distance
        }
    }

    @Test
    fun localFallback() {
        val servers = listOf(Server(0, "one #11", 12), Server(0, "two #22", 34))

        mockServers(null)

        val countDownLatch = CountDownLatch(1)
        val scenario = launchActivity<LoginActivity>().onActivity { activity ->
            val app = activity.application as App

            runBlocking {
                app.data.servers().setAll(servers)
            }

            countDownLatch.countDown()
        }

        countDownLatch.await(5, TimeUnit.SECONDS)

        onView(withId(R.id.name)).perform(closeSoftKeyboard())
        onView(withId(R.id.login)).perform(ViewActions.click())

        waitFor(withId(R.id.list), matches(isDisplayed()))

        onView(withId(R.id.list)).check(matches(hasChildCount(2)))
        onView(withText(servers[0].name)).check(matches(isDisplayed()))
        onView(withText(servers[1].name)).check(matches(isDisplayed()))
    }

    @Test
    fun localLoad() {
        val servers = listOf(Server(0, "one #11", 12), Server(0, "two #22", 34))

        mockServers(emptyList())

        val countDownLatch = CountDownLatch(1)
        val scenario = launchActivity<LoginActivity>().onActivity { activity ->
            val app = activity.application as App

            runBlocking {
                app.data.servers().setAll(servers)
            }

            app.online = false
            countDownLatch.countDown()
        }

        countDownLatch.await(5, TimeUnit.SECONDS)

        onView(withId(R.id.name)).perform(closeSoftKeyboard())
        onView(withId(R.id.login)).perform(ViewActions.click())

        waitFor(withId(R.id.list), matches(isDisplayed()))

        onView(withId(R.id.list)).check(matches(hasChildCount(2)))
        onView(withText(servers[0].name)).check(matches(isDisplayed()))
        onView(withText(servers[1].name)).check(matches(isDisplayed()))
    }

    @Test
    fun logout() {
        mockServers(emptyList())

        lateinit var app: App
        val scenario = launchActivity<ServerListActivity>().onActivity { activity ->
            app = activity.application as App
        }

        onView(withId(R.id.logout)).perform(ViewActions.click())

        waitFor(withId(R.id.password), matches(isDisplayed()))

        assertNull(app.user)

        onView(allOf(withId(R.id.name), withText("tesonet"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.password), withText(""))).check(matches(isDisplayed()))
    }

    private fun mockServers(list: List<Server>?) {
        val server = MockWebServer()
        server.start()
        Backend.url = server.url("/").toString()

        server.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                return when (request?.path) {
                    "/tokens" -> {
                        MockResponse().apply {
                            setResponseCode(200)
                            setBody("{\"token\":\"123\"}")
                        }
                    }
                    "/servers" -> {
                        MockResponse().apply {
                            if (list == null) {
                                setResponseCode(401)
                            } else {
                                setResponseCode(200)
                                setBody(list.toJson())
                            }
                        }
                    }
                    else -> {
                        MockResponse().setResponseCode(404)
                    }
                }
            }
        })
    }

}
