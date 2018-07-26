package com.teso.net.utils

import android.app.Activity
import android.view.View


fun View.onClick(body: () -> Unit) {
    setOnClickListener {
        hideKeyboard()
        body()
    }
}

fun View.hideKeyboard() {
    if (context is Activity) {
        val activity = context as Activity
        activity.hideKeyboard()
    }
}


