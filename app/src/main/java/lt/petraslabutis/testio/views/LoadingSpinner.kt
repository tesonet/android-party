package lt.petraslabutis.testio.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.view_loading_spinner.view.*
import lt.petraslabutis.testio.R

class LoadingSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var animator: ViewAnimator? = null

    init {
        View.inflate(context, R.layout.view_loading_spinner, this)
    }

    fun start() {
        stop()
        animator = ViewAnimator
            .animate(spinnerView)
            .interpolator(LinearInterpolator())
            .rotation(-360F)
            .repeatCount(-1)
            .duration(2000L)
            .start()
    }

    fun stop() {
        animator?.cancel()
    }

}