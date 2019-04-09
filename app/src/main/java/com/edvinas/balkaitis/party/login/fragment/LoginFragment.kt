package com.edvinas.balkaitis.party.login.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.base.BaseDaggerFragment
import com.edvinas.balkaitis.party.login.mvp.LoginContract
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : BaseDaggerFragment(), LoginContract.View {
    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            presenter.onLoginClicked(
                    usernameInput.text.toString(),
                    passwordInput.text.toString()
            )
        }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId() = R.layout.fragment_login

    override fun showLoadingView() {
        val initialConstraintSet = ConstraintSet().apply {
            clone(requireContext(), R.layout.fragment_login_loading)
        }

        initialConstraintSet.applyTo(root_layout)

        val animation = R.anim.rotate_animation
        loadingImage.startAnimation(AnimationUtils.loadAnimation(requireContext(), animation))
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
