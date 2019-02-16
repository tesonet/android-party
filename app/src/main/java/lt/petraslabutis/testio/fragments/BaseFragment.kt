package lt.petraslabutis.testio.fragments

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import lt.petraslabutis.testio.BuildConfig
import lt.petraslabutis.testio.R
import lt.petraslabutis.testio.TestioApplication
import lt.petraslabutis.testio.extensions.asApiException
import lt.petraslabutis.testio.extensions.toast
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseFragment: Fragment() {

    val disposables by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TestioApplication.applicationComponent.inject(this)
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        val animId = if (nextAnim != 0) nextAnim else if (enter) android.R.anim.fade_in else android.R.anim.fade_out
        val anim = AnimationUtils.loadAnimation(activity, animId)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) { if (enter) { onEnterAnimationEnd() } }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        return anim
    }

    open fun onEnterAnimationEnd() {}

    fun handleError(error: Throwable) {
        if (error is UnknownHostException) {
            context?.toast(resources.getString(R.string.error_no_connection))
        } else if (error is HttpException) {
            error.asApiException()?.let {
                context?.toast(it.message)
            }
        }
        if (BuildConfig.DEBUG) {
            error.printStackTrace()
        }
    }

    abstract fun inject()

}