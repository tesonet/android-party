package lt.marius.testio.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import lt.marius.testio.LoginEvent
import lt.marius.testio.R
import org.greenrobot.eventbus.EventBus

/**
 * Created by marius on 17.8.21.
 */
class LoginFragment : BaseFragment() {
	override val layoutId = R.layout.fragment_login

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		loginFormLogin.setOnClickListener {
			validateAndRun()
		}

	}

	private fun validateAndRun() {
		if (validate()) {
			login()
		}
	}

	private fun login() {
		EventBus.getDefault().post(LoginEvent(username = loginFormUsername.text.toString(),
		                                      password = loginFormPassword.text.toString()))
	}


	private fun validate(): Boolean {
		var errorView: View? = null
		if (loginFormPassword.text.isBlank()) {
			errorView = loginFormPassword
			loginFormPassword.error = getString(R.string.login_form_error_required)
		}
		if (loginFormUsername.text.isBlank()) {
			errorView = loginFormUsername
			loginFormUsername.error = getString(R.string.login_form_error_required)
		}
		errorView?.requestFocus()
		return errorView == null
	}

}