package lt.havefun.tesonetfunparty.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lt.havefun.tesonetfunparty.data.ErrorType
import lt.havefun.tesonetfunparty.data.IPreferencesManager
import lt.havefun.tesonetfunparty.data.LoginRepository
import retrofit2.HttpException

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val preferencesManager: IPreferencesManager
): ViewModel() {
    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val loginRepository: LoginRepository,
        private val preferencesManager: IPreferencesManager
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(
                loginRepository = loginRepository,
                preferencesManager = preferencesManager
            ) as T
        }
    }

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    private val _responseError = MutableLiveData<ErrorType>()
    val responseError: LiveData<ErrorType> = _responseError

    private val _validationError = MutableLiveData<String>()
    val validationError: LiveData<String> = _validationError

    private var subscription = CompositeDisposable()

    fun login(username: String, password: String) {
        if (!validValues(username, password)) {
            _validationError.postValue("")
            return
        }
        loginRepository.getToken(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccessfulLogin(it.token)
            }, { e ->
                if (isUnauthorizedError(e)) {
                    _responseError.postValue(ErrorType.CREDENTIALS_ERROR)
                } else {
                    _responseError.postValue(ErrorType.OTHER_ERROR)
                }
            })
            .also {
                subscription.add(it)
            }
    }

    private fun onSuccessfulLogin(token: String) {
        preferencesManager.saveToken(token)
        _data.postValue(token)
    }

    private fun validValues(
        username: String,
        password: String
    ): Boolean =
        username.isNotEmpty() && password.isNotEmpty()

    private fun isUnauthorizedError(e: Throwable): Boolean {
        if (e is HttpException) {
            if (e.code() == 401) {
                return true
            }
        }
        return false
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }
}