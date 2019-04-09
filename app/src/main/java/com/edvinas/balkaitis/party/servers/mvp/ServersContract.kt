package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.utils.mvp.BasePresenter

interface ServersContract {
    interface View {
    }

    interface Presenter : BasePresenter<View> {
    }
}
