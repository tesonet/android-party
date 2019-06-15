package com.mariussavickas.android_party.login

import android.os.Bundle
import android.view.View
import android.widget.*
import com.mariussavickas.android_party.R
import io.reactivex.android.schedulers.AndroidSchedulers
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mariussavickas.android_party.ApiController
import com.mariussavickas.android_party.persistance.User
import com.mariussavickas.android_party.serverList.ServerListActivity


class LoginActivity : AppCompatActivity() {

    lateinit var etLoginUsername: EditText
    lateinit var etLoginPassword: EditText
    lateinit var ivLogo: ImageView
    lateinit var llLoginForm: LinearLayout
    lateinit var pbLoading: ProgressBar
    lateinit var tvLoadingText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.login)

        llLoginForm = findViewById(R.id.ll_login_form)
        ivLogo = findViewById(R.id.iv_login_logo)
        pbLoading = findViewById(R.id.pb_login_fetching_loading)
        tvLoadingText = findViewById(R.id.tv_login_fetching_text)

        etLoginUsername = findViewById(R.id.et_login_username)
        etLoginPassword = findViewById(R.id.et_login_password)

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            llLoginForm.visibility = View.INVISIBLE
            ivLogo.visibility = View.INVISIBLE

            pbLoading.visibility = View.VISIBLE
            tvLoadingText.visibility = View.VISIBLE

            val user = User(
                etLoginUsername.text.toString(),
                etLoginPassword.text.toString(),
                null
            )

            ApiController.fetchAccessToken(user)
                .flatMap { user ->
                    ApiController.fetchServerList(user)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ serverList ->
//                    val intent = Intent(this@LoginActivity, ServerListActivity::class.java)
//                    this@LoginActivity.startActivity(intent)
                }

        }
    }
}
