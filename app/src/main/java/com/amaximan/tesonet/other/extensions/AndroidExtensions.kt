package com.amaximan.tesonet.other.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.amaximan.tesonet.R

fun FragmentActivity.loadFragment(fragment: Fragment, addToBackStack: Boolean) {
    if (addToBackStack) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    } else {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}