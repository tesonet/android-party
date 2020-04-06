package com.example.androidparty.utils

import android.content.Context
import android.net.ConnectivityManager

fun isConnectedToInternet(context: Context) : Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}