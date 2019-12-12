package com.giedrius.androidparty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giedrius.androidparty.R
import com.giedrius.androidparty.ui.MainActivityContract.*


class MainActivity : AppCompatActivity(), View {

    private var presenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
    }
}
