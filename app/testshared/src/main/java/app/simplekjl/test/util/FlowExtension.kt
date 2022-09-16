package app.simplekjl.test.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> Flow<T>.collectSingleValue(
    dispatcher: CoroutineDispatcher,
    timeout: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    val job = CoroutineScope(dispatcher).launch {
        collect {
            value = it
            latch.countDown()
        }
    }

    latch.await(timeout, timeUnit)
    job.cancel()

    return value
}
