package assignment.tesonet.homework

import android.view.View
import android.view.ViewPropertyAnimator

fun View.animateView(): ViewPropertyAnimator {
    return animate().alpha(0f).setDuration(500)
}