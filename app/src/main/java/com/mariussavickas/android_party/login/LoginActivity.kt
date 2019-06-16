package com.mariussavickas.android_party.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.mariussavickas.android_party.R
import io.reactivex.android.schedulers.AndroidSchedulers
import androidx.appcompat.app.AppCompatActivity
import com.mariussavickas.android_party.Repository
import com.mariussavickas.android_party.persistance.User
import com.mariussavickas.android_party.serverList.ServerListActivity
import android.app.Activity
import com.codecave.outmatch.shared.exceptions.HttpCodeException
import com.mariussavickas.android_party.RootApplication
import org.aviran.cookiebar2.CookieBar


class LoginActivity : AppCompatActivity(), Login {

    private val SERVER_ACTIVITY_ID = 1
    private lateinit var flFragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.login)

        flFragmentContainer = findViewById(R.id.fl_fragment_container)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .add(R.id.fl_fragment_container, LoginFormFragment())
            .commit()
    }

    override fun onLogin(user: User) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.fl_fragment_container, LoginLoadingFragment())
            .commit()

        val repository = (application as RootApplication).repository
        repository.fetchAccessToken(user)
            .flatMap { user ->
                repository.fetchServerList(user)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ serverList ->
                val intent = Intent(this@LoginActivity, ServerListActivity::class.java)
                this@LoginActivity.startActivityForResult(intent, SERVER_ACTIVITY_ID)
            }, { error ->
                var message = "Unexpected error"
                if (error is HttpCodeException) {
                    when (error.code) {
                        401 -> message = "Bad username or password"
                    }
                }

                CookieBar.Build(this)
                    .setDuration(4000)
                    .setTitle("Error")
                    .setMessage(message)
                    .setBackgroundColor(R.color.colorRed)
                    .show()

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fl_fragment_container, LoginFormFragment())
                    .commit()
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SERVER_ACTIVITY_ID) {
            if (resultCode == Activity.RESULT_OK || resultCode == Activity.RESULT_CANCELED) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_fragment_container, LoginFormFragment())
                    .commitAllowingStateLoss()
            }
        }
    }
}
