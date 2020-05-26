package com.baruckis.androidparty.presentation.util

import android.util.Log
import com.baruckis.androidparty.presentation.BuildConfig

const val LOG_TAG = "AndroidParty"

/**
 * Debug console logger for verbose message.
 *
 * @param message
 */
fun logConsoleVerbose(message: String) {
    if (BuildConfig.DEBUG) {
        Log.v(LOG_TAG, message)
    }
}

/**
 * Debug console logger for debug message.
 *
 * @param message
 */
fun logConsoleDebug(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(LOG_TAG, message)
    }
}

/**
 * Debug console logger for warning message.
 *
 * @param message
 */
fun logConsoleWarn(message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(LOG_TAG, message)
    }
}

/**
 * Debug console logger for error message.
 *
 * @param message
 */
fun logConsoleError(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(LOG_TAG, message)
    }
}