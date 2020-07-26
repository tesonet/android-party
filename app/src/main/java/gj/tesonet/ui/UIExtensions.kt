package gj.tesonet.ui

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import java.util.logging.Level

fun Activity.show(message: Message) {
    when (message.level) {
        Level.SEVERE, Level.WARNING -> snack(message.text, Snackbar.LENGTH_INDEFINITE) {}
        Level.INFO, Level.CONFIG -> snack(message.text, Snackbar.LENGTH_LONG)
        Level.FINE, Level.FINER, Level.FINEST,
        Level.ALL -> snack(message.text, Snackbar.LENGTH_SHORT)
    }
}

fun Activity.snack(
    message: String,
    duration: Int,
    @StringRes action: Int = android.R.string.ok,
    listener: (() -> Unit)? = null) {

    findViewById<View>(android.R.id.content)?.let{
        Snackbar.make(it, message, duration).apply {
            listener?.let { block ->
                setAction(action) {
                    block()
                }
            }
        }
        .setMultiline()
        .show()
    }
}

inline fun Snackbar.setMultiline(): Snackbar = this.apply {
    (view.findViewById(com.google.android.material.R.id.snackbar_text) as? TextView)
        ?.setSingleLine(false)
}
