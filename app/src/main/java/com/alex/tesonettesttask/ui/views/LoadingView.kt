package com.alex.tesonettesttask.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.alex.tesonettesttask.R
import kotlinx.android.synthetic.main.loading_view.view.*

class LoadingView constructor(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.loading_view, this) as LinearLayout
    }

    fun setLoadingText(message: String) {
        status.text = message
    }

}