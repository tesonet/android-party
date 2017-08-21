package lt.marius.testio

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import lt.marius.testio.api.AppServiceFactory
import lt.marius.testio.mock.MockAppService
import lt.marius.testio.mock.MockAppServiceFactory
import lt.marius.testio.persistance.UserPreferences
import lt.marius.testio.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

	@get:Rule
	val mainActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

	@Before
	fun setUp() {
		UserPreferences(mainActivityRule.activity).authorization = null
		AppServiceFactory.mockAppServiceFactory = MockAppServiceFactory()
		Espresso.registerIdlingResources(mainActivityRule.activity.appService as IdlingResource)
	}

	@After
	fun breakDown() {
		UserPreferences(mainActivityRule.activity).authorization = null
		Espresso.unregisterIdlingResources(mainActivityRule.activity.appService as IdlingResource)
	}


	@Test
	fun testLoginError() {
		onView(withId(R.id.loginFormUsername)).perform(typeText("klaida"))
		Espresso.closeSoftKeyboard()
		onView(withId(R.id.loginFormPassword)).perform(typeText("klaida"))
		Espresso.closeSoftKeyboard()
		onView(withId(R.id.loginFormLogin)).perform(click())
		Espresso.closeSoftKeyboard()
		onView(withText("Unauthorized")).check(matches(isDisplayed()))
	}

	@Test
	fun testLoginSuccess() {
		onView(withId(R.id.loginFormUsername)).perform(typeText(MockAppService.CORRECT_USERNAME))
		Espresso.closeSoftKeyboard()
		onView(withId(R.id.loginFormPassword)).perform(typeText(MockAppService.CORRECT_PASSWORD))
		Espresso.closeSoftKeyboard()
		onView(withId(R.id.loginFormLogin)).perform(click())
		Espresso.closeSoftKeyboard()
		onView(withId(R.id.serverRecycler)).check(matches(hasDescendant(withText("Lithuania #2"))))
		onView(withId(R.id.serverRecycler)).check(matches(hasDescendant(withText("900 km"))))
	}

	@Test
	fun testLogout() {
		//must login.. probably should mock that.. but, for the sake of simplicity
		testLoginSuccess()

		onView(withId(R.id.logoutButton)).perform(click())
		onView(withId(R.id.loginFormLogin)).check (matches(isDisplayed()))

	}

}
