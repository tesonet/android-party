package com.tesonet.testio.extension

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.widget.Toast as AndroidToast


fun Context.toast(@StringRes stringId: Int) {
    AndroidToast.makeText(this, stringId, AndroidToast.LENGTH_LONG).show()
}

fun Fragment.toast(@StringRes stringId: Int) {
    AndroidToast.makeText(this.context, stringId, AndroidToast.LENGTH_LONG).show()
}
