package place.holder.androidparty.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ImageView
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

fun View.fadeInWithTranslation(x: Float, y: Float, duration: Int) {
    visibility = View.VISIBLE
    animate().apply {
        alpha(1f)
        translationX(x)
        translationY(y)
        interpolator = LinearOutSlowInInterpolator()
        setDuration(duration.toLong())
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                visibility = View.VISIBLE
            }
        })
    }.start()
}

fun View.fadeOutWithTranslation(x: Float, y: Float, duration: Int) {
    animate().apply {
        alpha(0f)
        translationX(x)
        translationY(y)
        interpolator = FastOutLinearInInterpolator()
        setDuration(duration.toLong())
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                visibility = View.INVISIBLE
            }
        })
    }.start()
}

fun View.fadeIn(duration: Int) = fadeInWithTranslation(0f, 0f, duration)

fun View.fadeOut(duration: Int) = fadeOutWithTranslation(0f, 0f, duration)

fun ImageView.zoom(scale: Float, duration: Int, onEnd: () -> Unit = {}) {
    animate().apply {
        scaleX(scale)
        scaleY(scale)
        setDuration(duration.toLong())
        interpolator = FastOutSlowInInterpolator()
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                onEnd()
            }
        })
    }
}
