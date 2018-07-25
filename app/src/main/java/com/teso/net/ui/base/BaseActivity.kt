package com.teso.net.ui.base

import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.teso.net.utils.Constants
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    private var lastClickTime = SystemClock.elapsedRealtime()

    override fun onStart() {
        super.onStart()
        Timber.d(" ==> Activity started - ${this.localClassName}")
    }

    override fun onStop() {
        super.onStop()
        Timber.d(" <== Activity stopped - ${this.localClassName}")
    }

    fun isClickAllowed(): Boolean {
        val now = SystemClock.elapsedRealtime()
        return if (now - lastClickTime > Constants.CLICK_TIME_INTERVAL) {
            lastClickTime = now
            true
        } else
            false
    }
}