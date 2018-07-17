package levkovskiy.dev.tesonettest.ui.main

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.mvp.presenter.MainPresenter
import levkovskiy.dev.tesonettest.mvp.view.MainView
import levkovskiy.dev.tesonettest.net.Model
import levkovskiy.dev.tesonettest.utils.PreferenceHelper

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import levkovskiy.dev.tesonettest.ui.main.MainAdapter.ViewHolder
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  var mainActivityRule = ActivityTestRule(MainActivity::class.java, true, false)


  private var mainPresenter: MainPresenter = mock()
  lateinit var mainView: MainView

  @Before
  fun setup() {

    mainView = mock(MainView::class.java)

    //`when`(PreferenceHelper.token).thenReturn("TOKEN")
//    `when`<String>(
//        PreferenceHelper.getStringPreference(
//            getInstrumentation().context,
//            PreferenceHelper.token
//        )
//    ).thenReturn("test_token")
    //		PreferenceHelper.Companion.setStringPreference(mainActivityRule.getActivity(),
    //			PreferenceHelper.Companion.getToken(), "f9731b590611a5a9377fbd02f247fcdf");
    //mainActivityRule.launchActivity(null)

    doThrow(Exception()).`when`(mainPresenter).getServers()


  }

  @Test
  fun getServerTest() {
    verify(mainPresenter).getServers()
    onView(withId(R.id.serverList)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
  }
}
