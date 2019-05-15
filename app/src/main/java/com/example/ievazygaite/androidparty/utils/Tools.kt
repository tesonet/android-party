package com.example.ievazygaite.androidparty.utils

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView

class Tools{
    companion object {
        fun showSnackBar(message: String, color: Int, view:View) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setActionTextColor(color)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.WHITE)
            val textView =
                snackbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            textView.setTextColor(color)
            snackbar.show()
        }
    }
}