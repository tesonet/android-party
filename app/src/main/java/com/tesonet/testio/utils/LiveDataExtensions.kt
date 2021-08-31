package com.tesonet.testio.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeIt(owner: Fragment, callback: ((t: T?) -> Unit)) {
    this.observe(owner.viewLifecycleOwner, { value -> callback(value) })
}