package lt.mediapark.driveadvice.custom

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.widget.ProgressBar
import com.example.lukas.tesonettest.R



/**
 * Created by mediapark on 18/04/16.
 */
class ColoredProgressBar : ProgressBar {
    private var progressColor: Int = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    internal fun init(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(attrs,
                                                     R.styleable.ColoredProgressBar, 0, 0)
        try {
            progressColor = a.getColor(R.styleable.ColoredProgressBar_progressColor, Color.WHITE)
        } finally {
            a.recycle()
        }
        indeterminateDrawable.setColorFilter(progressColor, android.graphics.PorterDuff.Mode.SRC_IN)
    }

    fun setProgressColor(color: Int) {
        progressColor = color
        indeterminateDrawable.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)

    }
}
