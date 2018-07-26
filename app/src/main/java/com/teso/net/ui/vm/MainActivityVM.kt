package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.ErrorModel
import com.teso.net.data_flow.interactions.ITokenInteractor
import com.teso.net.ui.base.BaseFragment
import com.teso.net.ui.base.BaseViewModel
import com.teso.net.ui.fragment.LoadingFragment
import com.teso.net.ui.fragment.LoginFragment
import com.teso.net.utils.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class MainActivityVM : BaseViewModel() {

    @Inject lateinit var tokenInteractor: ITokenInteractor

    private val error: SingleLiveEvent<ErrorModel> = SingleLiveEvent()

    private val nextScreen: SingleLiveEvent<Class<out BaseFragment>> = SingleLiveEvent()

    init {
        AndroidApplication.component.inject(this)
    }

    fun getNextScreen(): LiveData<Class<out BaseFragment>> = nextScreen

    fun checkIfUserAlreadyLogin() {
        if (tokenInteractor.hasPassword().and(tokenInteractor.hasUserName())) {
            Timber.d("User already login")
            nextScreen.postValue(LoadingFragment::class.java)
        } else {
            nextScreen.postValue(LoginFragment::class.java)
        }
    }
}