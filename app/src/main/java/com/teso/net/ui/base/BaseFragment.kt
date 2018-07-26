package com.teso.net.ui.base

import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.teso.net.R
import com.teso.net.utils.Constants
import timber.log.Timber


abstract class BaseFragment : Fragment() {

    private var lastClickTime = SystemClock.elapsedRealtime()

    fun show(fragmentManager: FragmentManager?, addToBackStack: Boolean = true,
             sharedView: View? = null, sharedName: String? = null) {
        if (fragmentManager != null) {
            replaceFragment(fragmentManager, addToBackStack, sharedView, sharedName, getContainer())
        } else {
            Timber.e("Fragment manager is Null")
        }
    }

    private fun replaceFragment(fragmentManager: FragmentManager, addToBackStack: Boolean,
                                sharedView: View? = null, sharedName: String? = null, container: Int) {
        fragmentManager.beginTransaction().apply {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
            if (sharedView != null && sharedName != null) addSharedElement(sharedView, sharedName)
            replace(container, this@BaseFragment, getName())
            if (addToBackStack) addToBackStack(getName())
            commit()
        }
    }

    abstract fun getName(): String

    protected open fun getContainer(): Int = R.id.fragmentContainer

    protected fun isClickAllowed(): Boolean {
        val now = SystemClock.elapsedRealtime()
        return if (now - lastClickTime > Constants.CLICK_TIME_INTERVAL) {
            lastClickTime = now
            true
        } else
            false
    }

    override fun onResume() {
        super.onResume()
        Timber.d(" -- > Fragment resumed - ${getName()}")
    }

    override fun onPause() {
        super.onPause()
        Timber.d(" < -- Fragment paused - ${getName()}")
    }
}