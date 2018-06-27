package assignment.tesonet.homework.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import assignment.tesonet.homework.ServerResponse
import assignment.tesonet.homework.api.ApiService
import assignment.tesonet.homework.api.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private var service: ApiService) : ViewModel(), ViewModelResponse {

    override val loginResponse = MutableLiveData<Int>()
    var token: String? = null

    fun login(username: String, password: String) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            service.login(username, password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                    when (response?.isSuccessful) {
                        true -> {
                            token = response.body()?.token
                            loginResponse.value = ServerResponse.SUCCESS
                        }
                        false -> loginResponse.value = ServerResponse.ERROR
                    }
                }
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    loginResponse.value = ServerResponse.ERROR
                }
            })
        }
    }
}