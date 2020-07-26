package gj.tesonet

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.gson.GsonBuilder
import okio.Buffer
import org.hamcrest.Matcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.io.InputStreamReader

// alternative to IdlingResource, which is cumbersome to use
fun waitFor(matcher: Matcher<View>,
            assertion: ViewAssertion = ViewAssertions.matches(ViewMatchers.isDisplayed()),
            seconds: Int = 10) {

    repeatFor(seconds) { counter ->
        try {
            Espresso.onView(matcher).check(assertion)
            false
        } catch (e: Throwable) {
            if (counter == seconds) throw e
            true
        }
    }
}

fun repeatFor(seconds: Int = 10, block: (Int) -> Boolean) {
    var counter = 0

    while (counter < seconds && block(counter)) {
        counter++
        Thread.sleep(1000)
    }
}

inline fun <reified T> Buffer.fromJson(): T {
    val gson = GsonBuilder().create()

    return gson.fromJson(InputStreamReader(inputStream()), T::class.java)
}

inline fun <reified T> T.toJson(): String {
    val gson = GsonBuilder().create()

    return gson.toJson(this)
}

fun <T> assertListEquals(one: List<T>, two: List<T>, equal: (T, T) -> Boolean) {
    assertEquals(one.size, two.size)

    assertTrue(
        one.all { a ->
            two.any { b ->
                equal(a, b)
            }
        }
    )

    assertTrue(
        two.all { a ->
            one.any { b ->
                equal(a, b)
            }
        }
    )
}
