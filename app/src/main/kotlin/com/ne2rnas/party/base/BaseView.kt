package com.ne2rnas.party.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context

interface BaseView : LifecycleOwner {

    fun getContext(): Context
}
