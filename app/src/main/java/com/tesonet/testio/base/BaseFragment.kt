package com.tesonet.testio.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tesonet.testio.extension.genericTypeClass
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


abstract class BaseFragment<T: ViewModel>: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<T>
    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(genericTypeClass())
    }
}