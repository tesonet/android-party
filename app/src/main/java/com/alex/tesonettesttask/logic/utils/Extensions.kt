package com.alex.tesonettesttask.logic.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.alex.tesonettesttask.R


fun View.show() {
    if (this.alpha != 1f)
        ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).setDuration(250).start()
}

fun View.hide() {
    if (this.alpha != 0f)
        ObjectAnimator.ofFloat(this, "alpha", 1f, 0f).setDuration(250).start()
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun FragmentActivity.presentFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commit()

}



