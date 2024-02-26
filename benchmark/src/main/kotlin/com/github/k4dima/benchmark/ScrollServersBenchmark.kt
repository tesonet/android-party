@file:OptIn(ExperimentalMetricApi::class)

package com.github.k4dima.benchmark

import android.graphics.Point
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.TraceSectionMetric
import androidx.benchmark.macro.TraceSectionMetric.Mode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollServersBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()
    private val startupMode = StartupMode.WARM
    private val iterations = 5
    private val setupBlock: MacrobenchmarkScope.() -> Unit = {
        pressHome()
        startActivityAndWait()
        device.wait(Until.hasObject(By.res(SERVERS_SERVERS)), TIMEOUT)
            .let { Assert.assertTrue(it) }
    }
    private val measureBlock: MacrobenchmarkScope.() -> Unit = {
        val feed = device.wait(Until.findObject(By.res(SERVERS_SERVERS)), TIMEOUT)
        feed.setGestureMargin(device.displayWidth / 3)
        // feed.fling(Direction.DOWN)
        val displayMetrics = InstrumentationRegistry.getInstrumentation()
            .context
            .resources
            .displayMetrics
        feed.drag(Point(feed.visibleCenter.x, 0), (500 * displayMetrics.density).toInt())
    }

    @Test
    fun scrollJit() = benchmarkRule.measureRepeated(
        packageName,
        listOf(
            FrameTimingMetric(),
            TraceSectionMetric("JIT compiling %", Mode.Sum)
        ),
        startupMode = startupMode,
        iterations = iterations,
        setupBlock = setupBlock,
        measureBlock = measureBlock
    )

    @Test
    fun scrollFull() = benchmarkRule.measureRepeated(
        packageName,
        listOf(FrameTimingMetric()),
        CompilationMode.Full(),
        startupMode,
        iterations,
        setupBlock,
        measureBlock
    )

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() = signIn()
        private const val SERVERS_SERVERS = "servers:servers"
    }
}