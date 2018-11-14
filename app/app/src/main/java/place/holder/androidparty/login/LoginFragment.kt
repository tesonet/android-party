package place.holder.androidparty.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.view.*
import place.holder.androidparty.GlideApp
import place.holder.androidparty.R

class LoginFragment : Fragment() {

    private var serversProvider: ServersProvider? = null

    private var usernameTextChangeWatcher: LoginInputTextWatcher? = null
    private var passwordTextChangeWatcher: LoginInputTextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serversProvider = ServersProvider(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        usernameTextChangeWatcher = LoginInputTextWatcher(view.usernameEditText)
        passwordTextChangeWatcher = LoginInputTextWatcher(view.passwordEditText)
        GlideApp.with(this)
            .load(R.drawable.bg)
            .centerCrop()
            .into(view.backgroundImageView)
        return view
    }

    override fun onStart() {
        super.onStart()
        view!!.apply {
            usernameEditText.addTextChangedListener(usernameTextChangeWatcher)
            passwordEditText.addTextChangedListener(passwordTextChangeWatcher)
            loginButton.setOnClickListener {
                if (validateCredentials()) {
                    serversProvider?.login(
                        usernameEditText.text.toString(),
                        passwordEditText.text.toString(), { token -> warningTextView.text = token },
                        { warningTextView.setText(R.string.login_incorrect_credentials) },
                        { warningTextView.setText(R.string.login_server_error) },
                        LOGIN_REQUEST_TAG
                    )
                }
            }
        }
    }

    private fun validateCredentials(): Boolean {
        val usernameEmpty = view!!.usernameEditText.text.isNullOrBlank()
        val passwordEmpty = view!!.passwordEditText.text.isNullOrEmpty()
        val messageId = when {
            usernameEmpty && passwordEmpty -> R.string.login_empty_username_and_password
            passwordEmpty -> R.string.login_empty_password
            usernameEmpty -> R.string.login_empty_username
            else -> 0
        }
        return if (messageId == 0) {
            view!!.warningTextView.text = ""
            true
        } else {
            view!!.warningTextView.setText(messageId)
            false
        }
    }

    override fun onStop() {
        serversProvider?.cancelRequest(LOGIN_REQUEST_TAG)
        view!!.usernameEditText.removeTextChangedListener(usernameTextChangeWatcher)
        view!!.passwordEditText.removeTextChangedListener(passwordTextChangeWatcher)
        super.onStop()
    }

    override fun onDestroyView() {
        serversProvider?.cancelRequest(LOGIN_REQUEST_TAG)
        usernameTextChangeWatcher = null
        passwordTextChangeWatcher = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        serversProvider = null
        super.onDestroy()
    }

    companion object {
        private const val LOGIN_REQUEST_TAG = "Login Request"
    }
}