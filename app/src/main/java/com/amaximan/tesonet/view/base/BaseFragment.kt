package com.amaximan.tesonet.view.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amaximan.tesonet.view.contracts.RequiresLayoutRes

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment(), RequiresLayoutRes {
    var fragmentBinding: VDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        fragmentBinding?.setLifecycleOwner(this)
        return fragmentBinding?.root
    }
}