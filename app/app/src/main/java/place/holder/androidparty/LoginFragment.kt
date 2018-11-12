package place.holder.androidparty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    var usernameTextChangeWatcher: LoginInputTextWatcher? = null
    var passwordTextChangeWatcher: LoginInputTextWatcher? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        usernameTextChangeWatcher = LoginInputTextWatcher(view.usernameEditText)
        passwordTextChangeWatcher = LoginInputTextWatcher(view.passwordEditText)
        return view
    }

    override fun onStart() {
        super.onStart()
        view!!.usernameEditText.addTextChangedListener(usernameTextChangeWatcher)
        view!!.passwordEditText.addTextChangedListener(passwordTextChangeWatcher)
    }

    override fun onStop() {
        view!!.usernameEditText.removeTextChangedListener(usernameTextChangeWatcher)
        view!!.passwordEditText.removeTextChangedListener(passwordTextChangeWatcher)
        super.onStop()
    }

    override fun onDestroyView() {
        usernameTextChangeWatcher = null
        passwordTextChangeWatcher = null
        super.onDestroyView()
    }
}