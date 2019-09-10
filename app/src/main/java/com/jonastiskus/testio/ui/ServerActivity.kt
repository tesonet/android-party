package com.jonastiskus.testio.ui

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.jonastiskus.testio.R
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.databinding.ActivityServerBinding

class ServerActivity : BaseActivity() {

    private lateinit var binding : ActivityServerBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindowAnimations()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_server)
        enableToolbar()
        binding.data = parseExtras()
    }

    private fun parseExtras() : List<ServerGsonModel> {
        val gson = Gson()
        if (intent.extras != null) {
            val string = intent.extras!!.getString(LoadingActivity.JSON_EXTRA)
            return gson.fromJson(string, Array<ServerGsonModel>::class.java).asList()
        }

        return emptyList()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Snackbar.make(binding.root, R.string.TEXT_PRESS_BACK_ONE_MORE_TIME, Snackbar.LENGTH_LONG).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun setupWindowAnimations() {
        val fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade_transition)
        window.enterTransition = fade
    }

}
