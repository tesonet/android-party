package com.example.ievazygaite.androidparty.ui.base

import android.content.Context
import android.view.inputmethod.InputMethodManager

interface BaseContract {

    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun android.view.View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}