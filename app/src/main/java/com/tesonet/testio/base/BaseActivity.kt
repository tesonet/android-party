package com.tesonet.testio.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tesonet.testio.extension.genericTypeClass
import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseActivity<T: ViewModel>: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<T>
    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(genericTypeClass())
    }
}