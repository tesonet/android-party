package lt.petraslabutis.testio.extensions

import android.widget.TextView

fun String.widthInPx(textView: TextView): Int =
    textView.paint.measureText(if (textView.allCaps()) this@widthInPx.toUpperCase() else this@widthInPx).toInt()