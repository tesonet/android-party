package com.tesonet.testio.ui.loading

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tesonet.testio.R
import com.tesonet.testio.base.BaseFragment
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.repository.TokenRepository
import com.tesonet.testio.extension.toast
import com.tesonet.testio.ui.login.LoginFragment
import com.tesonet.testio.ui.serverslist.ServersListActivity


class LoadingFragment: BaseFragment<LoadingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getServers().observe(this::getLifecycle, this::onServersReceived)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    private fun onServersReceived(resource: Resource<List<Server>>) {
        when {
            resource.status == Resource.Status.ERROR -> handleLoadingError(resource.exception!!)
            resource.status == Resource.Status.SUCCESS -> goToServersList()
        }
    }

    private fun handleLoadingError(error: Throwable) {
        when (error) {
            is TokenRepository.BadCredentialsException -> toast(R.string.invalid_credentials)
            is TokenRepository.InternetRequiredException -> toast(R.string.internet_required)
            else -> toast(R.string.unexpected_error)
        }
        goToLogin()
    }

    private fun goToServersList() {
        activity?.finish()
        startActivity(Intent(context, ServersListActivity::class.java))
        activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun goToLogin() {
        fragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                ?.replace(R.id.container, LoginFragment())
                ?.commit()
    }
}