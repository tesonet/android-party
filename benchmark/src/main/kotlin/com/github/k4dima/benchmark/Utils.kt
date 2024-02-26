package com.github.k4dima.benchmark

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import java.util.concurrent.TimeUnit

fun signIn() {
    val context = ApplicationProvider.getApplicationContext<Application>()
    context.packageManager
        .getLaunchIntentForPackage(packageName)
        .let { context.startActivity(it) }
    InstrumentationRegistry.getInstrumentation()
        .let { UiDevice.getInstance(it) }
        .run {
            findObject(By.res("servers:signInOut")).click()
            wait(Until.findObject(By.text("Continue")), TIMEOUT)?.let { pressBack() }
            findObject(By.res("signin:username")).text = "tesonet"
            findObject(By.res("signin:password")).text = "partyanimal"
            wait(Until.findObject(By.text("Sign in")), TIMEOUT).click()
            wait(Until.findObject(By.text("Continue")), TIMEOUT)?.let { pressBack() }
        }
}

val TIMEOUT = TimeUnit.SECONDS.toMillis(4)
const val packageName = "com.github.k4dima.testio"