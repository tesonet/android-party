package com.amaximan.tesonet.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.amaximan.tesonet.R
import com.amaximan.tesonet.databinding.FragmentAuthBinding
import com.amaximan.tesonet.model.event.ServersLoadedEvent
import com.amaximan.tesonet.other.extensions.hideKeyboard
import com.amaximan.tesonet.other.extensions.loadFragment
import com.amaximan.tesonet.view.base.BaseFragment
import com.amaximan.tesonet.viewModel.AuthVM
import kotlinx.android.synthetic.main.fragment_auth.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun getLayoutResId() = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        fragmentBinding?.viewModel = ViewModelProviders.of(this).get(AuthVM::class.java)
    }

    private fun initClicks() {
        btn_log_in.setOnClickListener {
            et_username.clearFocus()
            et_password.clearFocus()
            activity?.hideKeyboard()

            fragmentBinding?.viewModel?.getToken()
        }
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onListsLoadedEvent(event: ServersLoadedEvent?) {
        EventBus.getDefault().removeAllStickyEvents()
        activity?.loadFragment(MainFragment(), false)
    }
}