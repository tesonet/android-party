package com.example.core.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.doNotLeak(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(RecyclerviewEventObserver(this))
}

internal class RecyclerviewEventObserver(private val recyclerView: RecyclerView) :
    LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            recyclerView.adapter = null
        }
    }
}

fun RecyclerView.asVerticalLayout(reverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, reverse)
}
