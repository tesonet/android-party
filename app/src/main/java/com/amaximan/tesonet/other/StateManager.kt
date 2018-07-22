package com.amaximan.tesonet.other

import android.databinding.ObservableField

class StateManager private constructor() {
    object Holder {
        val INSTANCE = StateManager()
    }

    companion object {
        val instance: StateManager by lazy { Holder.INSTANCE }
    }


    val getTokenIsInProgress = ObservableField<Boolean>(false)
    val getServersIsInProgress = ObservableField<Boolean>(false)
}