package place.holder.androidparty

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat

class LoginInputTextWatcher(private val editText: EditText) : TextWatcher {

    var empty: Boolean

    init {
        empty = editText.text.isNullOrEmpty()
        val icon = recolorInputIcon(editText.compoundDrawables.first { it != null }!!, empty)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null)
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.isNullOrBlank() != empty) {
            empty = s.isNullOrBlank()
            val icon = recolorInputIcon(editText.compoundDrawables.first { it != null }!!, empty)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null)
            } else {
                editText.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)
            }
        }
    }

    private fun recolorInputIcon(icon: Drawable, inputEmpty: Boolean): Drawable {
        val tintRes = if (inputEmpty) R.color.login_input_icon_tint_empty else R.color.login_input_icon_tint
        val recoloredIcon = DrawableCompat.wrap(icon)
        DrawableCompat.setTint(recoloredIcon, ResourcesCompat.getColor(editText.resources, tintRes, editText.context.theme))
        return recoloredIcon
    }
}