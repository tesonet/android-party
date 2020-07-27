package lt.havefun.tesonetfunparty.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import lt.havefun.tesonetfunparty.MainActivity
import lt.havefun.tesonetfunparty.R
import lt.havefun.tesonetfunparty.data.ErrorType
import lt.havefun.tesonetfunparty.events.LoggedInEvent
import lt.havefun.tesonetfunparty.ui.CustomFragment
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class LoginFragment: CustomFragment() {

    @Inject
    lateinit var viewModelFactory: LoginViewModel.Factory

    @Inject
    lateinit var eventBus: EventBus

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginBtn: Button
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        usernameET = view.findViewById(R.id.username)
        passwordET = view.findViewById(R.id.password)
        loginBtn = view.findViewById(R.id.login)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        loginBtn.setOnClickListener { _ ->
            viewModel.login(usernameET.text.toString(), passwordET.text.toString())
        }

        addObservers()
    }

    private fun addObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            eventBus.post(LoggedInEvent())
        })
        addResponseErrorObserver()
        addValidationErrorObserver()
    }

    private fun addResponseErrorObserver() {
        viewModel.responseError.observe(viewLifecycleOwner, Observer {
            val message: String
            if (it == ErrorType.CREDENTIALS_ERROR) {
                message = getString(R.string.wrong_credentials)
                usernameET.error = message
                passwordET.error = message
            } else {
                message = getString(R.string.error)
            }

            showToast(message)
        })
    }

    private fun addValidationErrorObserver() {
        viewModel.validationError.observe(viewLifecycleOwner, Observer {
            val message = getString(R.string.validation_error)
            if (usernameET.text.isEmpty()) {
                usernameET.error = message
            }
            if (passwordET.text.isEmpty()) {
                passwordET.error = message
            }
            showToast(message)
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG)
            .show()
    }
}