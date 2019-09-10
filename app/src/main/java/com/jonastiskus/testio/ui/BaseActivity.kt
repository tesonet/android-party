package com.jonastiskus.testio.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.jonastiskus.testio.R
import com.jonastiskus.testio.TestioApplication
import com.jonastiskus.testio.network.service.ServiceProvider

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var serviceProvider: ServiceProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testioApplication = application as TestioApplication
        serviceProvider = testioApplication.getServiceProvider()
    }

    fun enableToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.findViewById<ImageView>(R.id.logout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
