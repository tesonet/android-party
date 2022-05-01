package com.czech.androidparty.utils

import android.view.View

fun View.hide(onlyInvisible: Boolean = false) {
    this.visibility = if (onlyInvisible) View.INVISIBLE else View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.disableView() {
    animate()
        .alpha(0.5f).duration = 300
    isClickable = false
    isEnabled = false
    isFocusable = false
}

fun View.enableView() {
    animate()
        .alpha(1f).duration = 300
    isClickable = true
    isFocusable = true
    isEnabled = true
}