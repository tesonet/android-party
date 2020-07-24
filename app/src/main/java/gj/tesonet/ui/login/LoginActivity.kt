package gj.tesonet.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import gj.tesonet.BuildConfig
import gj.tesonet.R
import gj.tesonet.ui.AppActivity
import gj.tesonet.ui.servers.ServerListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppActivity() {

    private val model: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            val user = name.text?.toString() ?: return@setOnClickListener
            val pswd = password.text?.toString() ?: return@setOnClickListener

            model.login(user, pswd)
        }

        model.token.observe(this) {
            ServerListActivity.start(this)
        }

        model.user.observe(this) {
            name.setText(it?.name)
            password?.setText(it?.password)
        }

        model.message.observe(this) {
            show(it)
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
                .setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            or Intent.FLAG_ACTIVITY_TASK_ON_HOME)

            context.startActivity(intent)
        }
    }
}
