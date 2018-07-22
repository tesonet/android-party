package com.amaximan.tesonet.other.databinding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.EditText

object ViewDataBinding {

    @BindingAdapter("android:visibility")
    fun bindVisibility(view: View, value: Boolean?) {
        view.visibility = if (value == true) View.VISIBLE else View.GONE
    }

    @BindingAdapter("error")
    fun bindError(editText: EditText, value: String?) {
        editText.error = value
    }
}