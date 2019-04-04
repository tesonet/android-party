package com.edvinas.balkaitis.party.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.edvinas.balkaitis.party.R

fun FragmentActivity.replaceFragment(fragment: Fragment, container: Int = R.id.fragmentContainer) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment)
        .commit()
}
