package lt.marius.testio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		                WindowManager.LayoutParams.FLAG_FULLSCREEN)
		setContentView(R.layout.activity_login)


		loginFormLogin.setOnClickListener {
			validateAndRun()
		}

	}

	private fun validateAndRun() {
		loginProgressContainer.visibility = View.VISIBLE
		loginForm.visibility = View.GONE
		loginProgressMessage.setText(R.string.login_progress_message_login)
	}
}

