package com.teso.net.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.teso.net.R
import kotlinx.android.synthetic.main.login_edit_view.view.*

class LoginEditText @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0) : FrameLayout(context, attributeSet, defStyle) {

    var hint: String? = null
        set(value) {
            field = value
            logoEdit.hint  = hint ?: ""
        }
    var icon: Drawable? = null
        set(value) {
            field = value
            initView()
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.login_edit_view, this)
        initAttributes(attributeSet)
        initView()
    }

    private fun initAttributes(attrs: AttributeSet?) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.LoginEditText)

        hint = a.getString(R.styleable.LoginEditText_loginHint)

        if (a.hasValue(R.styleable.LoginEditText_loginIcon)) {
            icon = a.getDrawable(R.styleable.LoginEditText_loginIcon)
        }
        a.recycle()
    }

    private fun initView() {
        logoEdit.hint  = hint ?: ""
        if (icon != null) {
            logoEditIcon.setImageDrawable(icon)
        }
    }

    fun getText() = logoEdit.text.toString()
}
