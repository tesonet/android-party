package com.playground.ugnius.homework.views.components

import android.content.Context
import android.graphics.Color.RED
import android.util.AttributeSet
import android.graphics.Color.WHITE
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import com.github.florent37.viewanimator.AnimationBuilder
import com.github.florent37.viewanimator.ViewAnimator
import com.playground.ugnius.homework.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.loading_animation.view.*

class LoadingView constructor(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private companion object {
        const val LOOP_FRAME = 120
        const val ERROR_START_FRAME = 658
        const val ERROR_END_FRAME = 820
    }

    private val subject = PublishSubject.create<Int>()
    private val compositeDisposable = CompositeDisposable()

    init {
        inflate(context, R.layout.loading_animation, this) as LinearLayout
        loadingAnimation.speed = 1.5F
        loadingAnimation.addAnimatorUpdateListener {
            val frame = loadingAnimation.frame
            if (frame == LOOP_FRAME) {
                subject.onNext(LOOP_FRAME)
            } else if (frame == ERROR_END_FRAME) {
                cancelAnimation()
            }
        }
    }

    fun playLoadingAnimation(message: String) {
        information.text = message
        information.setTextColor(WHITE)
        loadingAnimation.setMinAndMaxFrame(1, 120)
        loadingAnimation.playAnimation()
        fade(views = *arrayOf(loadingAnimation, information), values = floatArrayOf(0F, 0.5F, 1F))
    }

    fun playErrorAnimation(message: String, onStop: () -> Unit = {}) {
        val disposable = subject.subscribe {
            loadingAnimation.setMinAndMaxFrame(ERROR_START_FRAME, ERROR_END_FRAME)
            Handler().postDelayed(
                {
                    information.setTextColor(RED)
                    information.text = message
                }
                , 700
            )
            fade(views = *arrayOf(loadingAnimation, information),
                values = floatArrayOf(1F, 0.5F, 0F),
                delay = 2000,
                onStop = { onStop() }
            )
        }
        compositeDisposable.add(disposable)
    }

    private fun cancelAnimation() {
        loadingAnimation.cancelAnimation()
        compositeDisposable.clear()
    }

    private fun fade(
        vararg views: View,
        values: FloatArray,
        duration: Long = 250,
        delay: Long = 0,
        onStart: () -> Unit = {},
        onStop: () -> Unit = {}
    ) {
        var animationBuilder: AnimationBuilder? = null
        views.forEachIndexed { index, view ->
            if (index == 0) {
                animationBuilder = ViewAnimator.animate(view).alpha(*values)
            } else {
                animationBuilder?.andAnimate(view)?.alpha(*values)
            }
        }
        animationBuilder?.duration(duration)
            ?.onStart(onStart)
            ?.onStop(onStop)
            ?.startDelay(delay)
            ?.start()
    }
}