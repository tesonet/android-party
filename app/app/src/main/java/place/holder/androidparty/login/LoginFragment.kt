package place.holder.androidparty.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import place.holder.androidparty.AppController
import place.holder.androidparty.GlideApp
import place.holder.androidparty.R
import place.holder.androidparty.common.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoginUi()
    }

    override fun onStart() {
        super.onStart()
        view!!.apply {
            usernameEditText.addTextChangedListener(usernameTextChangeWatcher)
            passwordEditText.addTextChangedListener(passwordTextChangeWatcher)
            loginButton.setOnClickListener {
                if (validateCredentials()) {
                    hideLoginUi()
                    postDelayed({
                        showProgressSplash(R.string.login_logging_in)
                        requestLogin()
                    }, ANIMATE_OUT.toLong())
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

    private fun requestLogin() {
        view?.apply {
            serversProvider?.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    { token ->
                        AppController.instance.token = token
                        progressTextView.setText(R.string.login_fetching_list)
                        requestServers()
                    },
                    {
                        warningTextView.setText(R.string.login_incorrect_credentials)
                        postDelayed({
                            hideProgressSplash()
                            postDelayed({ showLoginUi() }, ANIMATE_OUT.toLong())
                        }, ANIMATE_IN.toLong())
                    },
                    {
                        warningTextView.setText(R.string.login_server_error)
                        postDelayed({
                            hideProgressSplash()
                            postDelayed({ showLoginUi() }, ANIMATE_OUT.toLong())
                        }, ANIMATE_IN.toLong())
                    }, REQUEST_TAG
            )
        }
    }

    private fun requestServers() {
        view?.apply {
            serversProvider?.requestServers({
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                findNavController(this).navigate(LoginFragmentDirections.actionOpenServers(), navOptions)
            }, {
                AppController.instance.token = null
                warningTextView.setText(R.string.login_unauthorized)
                postDelayed({
                    hideProgressSplash()
                    postDelayed({ showLoginUi() }, ANIMATE_OUT.toLong())
                }, ANIMATE_IN.toLong())
            }, {
                warningTextView.setText(R.string.login_server_error)
                postDelayed({
                    hideProgressSplash()
                    postDelayed({ showLoginUi() }, ANIMATE_OUT.toLong())
                }, ANIMATE_IN.toLong())
            }, REQUEST_TAG)
        }
    }

    private fun showProgressSplash(messageResId: Int) {
        progressTextView.setText(messageResId)
        view?.apply {
            progressBar.fadeIn(ANIMATE_IN)
            progressTextView.fadeInWithTranslation(0f, 0f, ANIMATE_IN)
        }
    }

    private fun hideProgressSplash() {
        val translateY = resources.getDimension(R.dimen.login_animate_progress_text_translate_y)
        view?.apply {
            progressBar.fadeOut(ANIMATE_IN)
            progressTextView.fadeOutWithTranslation(0f, translateY, ANIMATE_OUT)
        }
    }

    private fun showLoginUi() {
        view?.apply {
            logoImageView.fadeIn(ANIMATE_IN)
            warningTextView.fadeInWithTranslation(0f, 0f, ANIMATE_IN)
            usernameEditText.fadeInWithTranslation(0f, 0f, ANIMATE_IN)
            passwordEditText.fadeInWithTranslation(0f, 0f, ANIMATE_IN)
            loginButton.fadeInWithTranslation(0f, 0f, ANIMATE_IN)
            backgroundImageView.zoom(1f, ANIMATE_IN) {
                GlideApp.with(this)
                        .load(R.drawable.bg)
                        .centerCrop()
                        .into(backgroundImageView)
            }
        }
    }

    private fun hideLoginUi() {
        val translateY = resources.getDimension(R.dimen.login_animate_login_ui_translate_y)
        view?.apply {
            logoImageView.fadeOut(ANIMATE_IN)
            warningTextView.fadeOutWithTranslation(0f, translateY, ANIMATE_OUT)
            usernameEditText.fadeOutWithTranslation(0f, translateY, ANIMATE_OUT)
            passwordEditText.fadeOutWithTranslation(0f, translateY, ANIMATE_OUT)
            loginButton.fadeOutWithTranslation(0f, translateY, ANIMATE_OUT)
            backgroundImageView.zoom(1.3f, ANIMATE_OUT)
        }
    }

    override fun onStop() {
        view!!.usernameEditText.removeTextChangedListener(usernameTextChangeWatcher)
        view!!.passwordEditText.removeTextChangedListener(passwordTextChangeWatcher)
        super.onStop()
    }

    override fun onDestroyView() {
        serversProvider?.cancelRequest(REQUEST_TAG)
        usernameTextChangeWatcher = null
        passwordTextChangeWatcher = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        serversProvider = null
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_TAG = "Login Request"

        private const val ANIMATE_IN = 250
        private const val ANIMATE_OUT = 200
    }
}