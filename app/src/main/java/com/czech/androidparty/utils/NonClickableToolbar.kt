package com.czech.androidparty.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.R
import androidx.appcompat.widget.Toolbar

class NonClickableToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttribute: Int = R.attr.toolbarStyle
): Toolbar(context, attributeSet, defStyleAttribute) {

    override fun onTouchEvent(ev: MotionEvent?) = false
}