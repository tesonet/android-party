package com.amaximan.tesonet.other.extensions

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.inputmethod.InputMethodManager
import com.amaximan.tesonet.R


fun FragmentActivity.loadFragment(fragment: Fragment, addToBackStack: Boolean) {
    if (addToBackStack) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    } else {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}

fun Activity.hideKeyboard() {
    currentFocus.windowToken?.let {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it, 0)
    }
}