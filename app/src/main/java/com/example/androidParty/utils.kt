package com.example.androidParty

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}


@BindingAdapter("distance")
fun bindDistance(textView: AppCompatTextView, distance: String) {
    textView.text = "$distance km "
}