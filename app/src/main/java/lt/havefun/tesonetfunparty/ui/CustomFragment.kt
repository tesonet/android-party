package lt.havefun.tesonetfunparty.ui

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment

abstract class CustomFragment: Fragment() {

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        val animation: Animation?
            if (nextAnim == 0) {
                animation = super.onCreateAnimation(transit, enter, nextAnim)
            } else {
                animation = AnimationUtils.loadAnimation(activity, nextAnim)
                view?.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

        animation?.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation) {
            }

            override fun onAnimationEnd(p0: Animation) {
                view?.setLayerType(View.LAYER_TYPE_NONE, null)
            }

            override fun onAnimationStart(p0: Animation) {
            }

        })
        return animation
    }


}