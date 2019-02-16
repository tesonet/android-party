package lt.petraslabutis.testio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import lt.petraslabutis.testio.viewmodels.AuthenticationViewModel
import lt.petraslabutis.testio.viewmodels.NavigationViewModel
import lt.petraslabutis.testio.viewmodels.ServerListViewModel
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var authenticationViewModel: AuthenticationViewModel
    @Inject
    lateinit var serverListViewModel: ServerListViewModel
    @Inject
    lateinit var navigationViewModel: NavigationViewModel

    private var switchAnimation: ViewAnimator? = null

    private companion object {
        const val ALPHA_CHANGE_DURATION = 500L
    }

    override fun inject() {
        TestioApplication.applicationComponent.inject(this@LoginFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_login, container, false).apply {
            if (BuildConfig.DEBUG) {
                usernameEditText.setText(BuildConfig.TEST_LOGIN_CREDENTIALS_USERNAME)
                passwordEditText.setText(BuildConfig.TEST_LOGIN_CREDENTIALS_PASSWORD)
            }
        }

    override fun onStart() {
        super.onStart()
        passwordEditText.onKeyboardDoneClick {
            loginButton.callOnClick()
        }.addTo(disposables)

        loginButton.onClick {
            activity?.closeKeyboard()
            startLoading()
            authenticationViewModel
                .login(usernameEditText.text.toString(), passwordEditText.text.toString())
                .flatMapCompletable { serverListViewModel.refreshServerData() }
                .scheduleNetworkCall()
                .doOnDispose { stopLoading() }
                .subscribeBy(onComplete = {
                    switchAnimation?.cancel()
                    navigationViewModel.replaceTopFragment(ServerListFragment().newInstance(true))
                }, onError = {
                    it.printStackTrace()
                    stopLoading()
                }).addTo(disposables)
        }.addTo(disposables)
    }

    fun startLoading() {
        loginButton.isClickable = false
        switchAnimation = ViewAnimator
            .animate(loginHolder)
            .alpha(loginHolder?.alpha ?: 0F, 0F)
            .onStop { loginHolder?.gone() }
            .duration(ALPHA_CHANGE_DURATION)
            .thenAnimate(loadingHolder)
            .alpha(loadingHolder?.alpha ?: 0F, 1F)
            .onStart {
                loadingHolder?.visible()
                progressBar?.start()
            }
            .duration(ALPHA_CHANGE_DURATION)
            .start()
    }

    fun stopLoading() {
        loginButton.isClickable = true
        switchAnimation = ViewAnimator
            .animate(loadingHolder)
            .alpha(loadingHolder?.alpha ?: 0F, 0F)
            .onStop {
                progressBar?.stop()
                loadingHolder?.gone()
            }
            .duration(ALPHA_CHANGE_DURATION)
            .thenAnimate(loginHolder)
            .alpha(loginHolder?.alpha ?: 0F, 1F)
            .onStart {
                loginHolder?.visible()
                loadingHolder?.alpha = 0F
            }
            .duration(ALPHA_CHANGE_DURATION)
            .start()
    }

}