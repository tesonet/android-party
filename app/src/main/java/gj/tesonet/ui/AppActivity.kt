package gj.tesonet.ui

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import gj.tesonet.R
import gj.tesonet.ui.login.LoginActivity
import java.util.logging.Level

open class AppActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.logout) {
            LoginActivity.start(this)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun show(message: Message) {
        when (message.level) {
            Level.SEVERE, Level.WARNING -> snack(message.text, Snackbar.LENGTH_INDEFINITE) {}
            Level.INFO, Level.CONFIG -> snack(message.text, Snackbar.LENGTH_LONG)
            Level.FINE, Level.FINER, Level.FINEST,
            Level.ALL -> snack(message.text, Snackbar.LENGTH_SHORT)
        }
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
