package com.alex.tesonettesttask.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.TesonetApplication
import com.alex.tesonettesttask.logic.model.ServersModel
import com.alex.tesonettesttask.logic.utils.presentFragment
import com.alex.tesonettesttask.ui.fragments.LoginFragment
import com.alex.tesonettesttask.ui.fragments.ServersFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var serverModel: ServersModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TesonetApplication.instance.mainComponent?.inject(this)
        setContentView(R.layout.activity_main)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            presentFragment(if (serverModel.hasCachedServers()) ServersFragment() else LoginFragment())
    }

}