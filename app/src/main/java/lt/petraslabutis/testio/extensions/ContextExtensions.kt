package lt.petraslabutis.testio.extensions

import android.content.Context
import android.widget.Toast

fun Context.dp(dp: Float): Float {
    return dp * resources.displayMetrics.density
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}