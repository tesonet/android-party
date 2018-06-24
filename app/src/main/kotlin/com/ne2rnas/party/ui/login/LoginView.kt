package com.ne2rnas.party.ui.login

import com.ne2rnas.party.base.BaseView

interface LoginView : BaseView {
    fun showError(error: String?)

    fun showLoading()

    fun hideLoading()

    fun openServersActivity()
}
