package net.justinas.tesonetapp.withlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.justinas.tesonetapp.withlib.view.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commit()
        }
    }
}
