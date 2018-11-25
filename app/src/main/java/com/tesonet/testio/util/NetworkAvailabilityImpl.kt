package com.tesonet.testio.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkAvailabilityImpl(
    private val context: Context
) : NetworkAvailability {

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}