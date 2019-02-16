package lt.petraslabutis.testio.extensions

import android.content.Context

fun Context.dp(dp: Float): Float {
    return dp * resources.displayMetrics.density
}