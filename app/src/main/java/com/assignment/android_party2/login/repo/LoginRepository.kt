package com.assignment.android_party2.login.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.android_party2.api.Api
import com.assignment.android_party2.login.models.TokenModel
import com.assignment.android_party2.login.ui.LoginCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    val TAG = LoginRepository::class.java.simpleName

    fun userLogin(
        username: String,
        password: String,
        loginCallback: LoginCallback?
    ): LiveData<TokenModel> {

        val loginResponse = MutableLiveData<TokenModel>()

        Api().userLogin(username, password).enqueue(object : Callback<TokenModel> {

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.isSuccessful && response.code() == 200) {
                    loginResponse.value = response.body()
                    loginCallback?.onSuccess(loginResponse)
                } else {
                    loginCallback?.onFailure("Invalid username or password!")
                }
            }

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                Log.e(TAG, "Authorization has failed, check logs.")
                t.printStackTrace()
            }
        }
        )
        return loginResponse
    }
}