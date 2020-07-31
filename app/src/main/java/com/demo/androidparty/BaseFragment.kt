package com.demo.androidparty

import android.os.Bundle
import android.view.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {

    internal abstract val layoutId: Int
    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)

    protected fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { data -> data?.let(action) })
    }

    protected fun <T> LiveData<T>.observeNullable(action: (T?) -> Unit) {
        observe(viewLifecycleOwner, Observer { data -> action(data) })
    }
}