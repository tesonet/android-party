package com.teso.net.utils

import android.app.Activity
import android.content.Intent
import android.support.annotation.ColorRes
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.teso.net.ErrorModel
import com.teso.net.R
import com.teso.net.ui.base.BaseFragment
import timber.log.Timber

fun Activity.showSnack(errorModel: ErrorModel?, length: Int = Snackbar.LENGTH_LONG) {
    val snackContainer: View? = findViewById(android.R.id.content)
    if (snackContainer != null && errorModel != null) {
        val msg: String = when {
            errorModel.throwable != null -> errorModel.throwable.message.toString()
            errorModel.string != null -> errorModel.string
            errorModel.stringId != null -> getString(errorModel.stringId)
            else -> getString(R.string.server_error)
        }
        Snackbar.make(snackContainer, msg, length).show()
    } else {
        Timber.e("android.R.id.content container not found in $localClassName")
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.goToNextScreen(activityClass: Class<out Activity>?) {
    if (activityClass == null) return
    startActivity(Intent(this, activityClass))
    finish()
}

fun FragmentActivity.showNewFragment(fragment: Class<out BaseFragment>?, addToBackStack: Boolean = true,
                                     sharedView: View? = null, sharedName: String? = null) {
    fragment?.let { fragment.newInstance()?.show(supportFragmentManager, addToBackStack, sharedView, sharedName) }
}

fun Activity.changeStatusBarColor(@ColorRes color: Int) {
    this.window?.statusBarColor = ContextCompat.getColor(this, color)
}
