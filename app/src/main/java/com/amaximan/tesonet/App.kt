package com.amaximan.tesonet

import android.app.Application
import android.databinding.DataBindingUtil
import android.widget.Toast
import com.amaximan.tesonet.other.databinding.MyDataBindingComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        DataBindingUtil.setDefaultComponent(MyDataBindingComponent())
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        lateinit var instance: App
    }
}