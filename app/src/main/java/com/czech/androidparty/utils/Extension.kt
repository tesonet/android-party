package com.czech.androidparty.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

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

fun Fragment.launchFragment(direction: NavDirections) = try {
    findNavController().navigate(direction)
} catch (e: Exception) {
    Log.e("NAVIGATION_ERROR", e.toString())
}

fun Fragment.showShortSnackBar(message: String) {
    val snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    val snackBarView: View = snackBar.view
    val snackTextView: TextView =
        snackBarView.findViewById(com.google.android.material.R.id.snackbar_text)
    snackTextView.maxLines = 3
    snackBar.show()
}

fun Activity.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showErrorDialog(message: String) {
    MaterialAlertDialogBuilder(this)
        .setMessage(message)
        .setPositiveButton("OK") { _, _ -> }
        .show()
}