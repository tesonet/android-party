package lt.petraslabutis.testio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.florent37.viewanimator.ViewAnimator
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import lt.petraslabutis.testio.BuildConfig
import lt.petraslabutis.testio.R
import lt.petraslabutis.testio.TestioApplication
import lt.petraslabutis.testio.extensions.*
import lt.petraslabutis.testio.viewmodels.LoginViewModel
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun inject() {
        TestioApplication.applicationComponent.inject(this@LoginFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_login, container, false).apply {

            if (BuildConfig.DEBUG) {
                usernameEditText.setText(BuildConfig.TEST_LOGIN_CREDENTIALS_USERNAME)
                passwordEditText.setText(BuildConfig.TEST_LOGIN_CREDENTIALS_PASSWORD)
            }

            passwordEditText.onKeyboardDoneClick(true) {
                loginButton.callOnClick()
            }.addTo(disposables)

            loginButton.onClick {
                startLoading()
                loginViewModel
                    .login(usernameEditText.text.toString(), passwordEditText.text.toString())
                    .scheduleNetworkCall()
                    .subscribeBy(onNext = {
                        println("token: " + it.token)
                    }, onError = {
                        it.printStackTrace()
                    }).addTo(disposables)
            }.addTo(disposables)
        }

    fun startLoading() {
        loginButton.isClickable = false
        ViewAnimator
            .animate(loginHolder)
            .alpha(loginHolder.alpha, 0F)
            .onStop { loginHolder.gone() }
            .duration(300L)
            .thenAnimate(loadingHolder)
            .alpha(loadingHolder.alpha, 1F)
            .onStart {
                loadingHolder.visible()
                progressBar.start()
            }
            .duration(300)
            .start()
    }

    fun stopLoading() {
        loginButton.isClickable = true
        ViewAnimator
            .animate(loadingHolder)
            .alpha(loadingHolder.alpha, 0F)
            .onStop { loadingHolder.gone() }
            .duration(300L)
            .thenAnimate(loginHolder)
            .alpha(loginHolder.alpha, 1F)
            .onStart {
                loginHolder.visible()
                progressBar.start()
            }
            .duration(300)
            .start()
    }

}