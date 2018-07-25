package com.teso.net.utils

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


fun View.onClick(body: () -> Unit) {
    setOnClickListener {
        hideKeyboard()
        body()
    }
}

fun View.hideKeyboard() {
    if (context is Activity) {
        val activity = context as Activity
        activity.hideKeyboard()
    }
}

fun ImageView.setImageOrGone(@DrawableRes imgId: Int) {
    visibility = if (imgId == 0) {
        setImageDrawable(null)
        View.GONE
    } else {
        setImageResource(imgId)
        View.VISIBLE
    }
}

fun ImageView.setImageOrGone(icon: Drawable?) {
    setImageDrawable(icon)
    visibility = if (icon == null) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

fun TextView.setTextOrGone(@StringRes stringId: Int) {
    visibility = if (stringId == 0) {
        text = ""
        View.GONE
    } else {
        setText(stringId)
        View.VISIBLE
    }
}

fun TextView.setTextOrGone(string: String?) {
    visibility = if (TextUtils.isEmpty(string)) {
        text = ""
        View.GONE
    } else {
        text = string
        View.VISIBLE
    }
}

fun View.disableBackPress() {
    this.apply {
        isFocusableInTouchMode = true
        requestFocus()
        setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK }
    }
}

fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

fun View.setVisibility(visible: Boolean?) {
    if (visible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.expand() {
    this.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val targetHeight = this.measuredHeight

    this.layoutParams.height = 1
    this.visibility = View.VISIBLE
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            this@expand.layoutParams.height = if (interpolatedTime == 1f)
                ViewGroup.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            this@expand.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = ((targetHeight / this.context.resources.displayMetrics.density).toInt()).toLong() * 3
    this.startAnimation(a)
}

fun View.collapse() {
    val initialHeight = this.measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                this@collapse.visibility = View.GONE
            } else {
                this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                this@collapse.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = (initialHeight / this.context.resources.displayMetrics.density).toInt().toLong() * 2
    this.startAnimation(a)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun View.onTouch(downAction: () -> Unit, upAction: () -> Unit) {
    this.setOnTouchListener(android.view.View.OnTouchListener { _, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> downAction()
            MotionEvent.ACTION_UP -> upAction()
            MotionEvent.ACTION_CANCEL -> upAction()
            else -> return@OnTouchListener false
        }
        true
    })
}

fun EditText.getInt(): Int = this.text.toString().toIntOrNull() ?: 0