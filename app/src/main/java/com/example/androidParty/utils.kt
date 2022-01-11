package com.example.androidParty

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

fun View.enable(enabled: Boolean) {
  isEnabled = enabled
  alpha = if (enabled) 1f else 0.5f
}