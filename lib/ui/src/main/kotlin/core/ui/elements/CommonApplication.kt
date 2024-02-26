package core.ui.elements

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.Q
import android.os.Build.VERSION_CODES.S
import android.os.StrictMode
import logcat.AndroidLogcatLogger
import logcat.LogPriority

fun commonApplicationInit(debug: Boolean, application: Application) {
    AndroidLogcatLogger.installOnDebuggableApp(application, LogPriority.VERBOSE)
    if (debug) strictMode()
}

private fun strictMode() {
    StrictMode.enableDefaults()
    val policy = StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectActivityLeaks()
        .detectLeakedClosableObjects()
        .detectLeakedRegistrationObjects()
        .detectFileUriExposure()
        .let { if (SDK_INT >= M) it.detectCleartextNetwork() else it }
        .let { if (SDK_INT >= O) it.detectContentUriWithoutPermission() else it }
        .let { if (SDK_INT >= Q) it.detectCredentialProtectedWhileLocked() else it }
        .let { if (SDK_INT >= S) it.detectIncorrectContextUse().detectUnsafeIntentLaunch() else it }
        .penaltyLog()
        .build()
    StrictMode.setVmPolicy(policy)
}