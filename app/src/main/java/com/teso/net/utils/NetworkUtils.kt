package com.teso.net.utils

import android.content.Context
import android.net.ConnectivityManager
import com.teso.net.data_flow.network.NetworkUnreachableExeption
import io.reactivex.Observable
import java.io.IOException


/**
 * Created by a.belichenko@gmail.com on 2/9/18.
 */
object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isInternetConnected(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }

    fun isInternetConnectedRx(): Observable<Boolean> =
            if (isInternetConnected()) {
                Observable.just(true)
            } else {
                Observable.error<Boolean>(NetworkUnreachableExeption())
            }
}