package com.teso.net.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import com.teso.net.BuildConfig


/**
 * Created by a.belichenko@gmail.com on 2/9/18.
 */
object DeviceUtils {

    fun getDeviceInfo(context: Context): String {
        return "App version ${getAppVersion()}\n" +
                "Device name ${getDeviceName()}\n" +
                "Device SDK version ${getDeviceApiLevel()}\n" +
                "Device ID ${getDeviceID(context)}\n"
    }

    fun getAppVersion(): String = BuildConfig.VERSION_NAME

    fun getDeviceID(context: Context): String = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else capitalize(manufacturer) + " " + model
    }

    fun getDeviceApiLevel(): String = Build.VERSION.SDK_INT.toString()

    private fun capitalize(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return str
        }
        val arr = str.toCharArray()
        var capitalizeNext = true

        val phrase = StringBuilder()
        for (c in arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c))
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true
            }
            phrase.append(c)
        }
        return phrase.toString()
    }
}