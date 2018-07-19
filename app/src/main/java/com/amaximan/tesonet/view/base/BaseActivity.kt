package com.amaximan.tesonet.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.amaximan.tesonet.view.contracts.RequiresLayoutRes

abstract class BaseActivity : AppCompatActivity(), RequiresLayoutRes {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }
}