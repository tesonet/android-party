package lt.marius.testio.mock

import android.support.test.espresso.IdlingResource
import io.reactivex.Observable
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by marius on 17.8.21.
 */
abstract class BaseIdlingResource : IdlingResource {
	private val counter: AtomicInteger = AtomicInteger(0)
	private val callbacks: MutableList<android.support.test.espresso.IdlingResource.ResourceCallback> = mutableListOf()

	override fun isIdleNow(): Boolean {
		return counter.get() == 0
	}

	override fun registerIdleTransitionCallback(callback: android.support.test.espresso.IdlingResource.ResourceCallback) {
		callbacks.add(callback)
	}


	fun <T> wrapObservable(obs: Observable<T>): Observable<T> {
		counter.incrementAndGet()
		return obs.doFinally {
			counter.decrementAndGet()
			notifyIdle()
		}
	}

	private fun notifyIdle() {
		if (counter.get() == 0) {
			for (cb in callbacks) {
				cb.onTransitionToIdle()
			}
		}
	}
}