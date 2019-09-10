package com.jonastiskus.testio.auth

import android.content.Context
import android.util.Log
import com.jonastiskus.testio.auth.AuthService.AuthServiceConstants.SHARED_PREFS
import com.jonastiskus.testio.auth.AuthService.AuthServiceConstants.TOKEN
import com.jonastiskus.testio.model.AuthGsonModel
import com.jonastiskus.testio.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService(context : Context, private val apiService: ApiService) {

    object AuthServiceConstants {
        const val SHARED_PREFS = "token_prefs"
        const val TOKEN = "token"
    }

    private var sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun authorizeUser(username : String, password : String, callback: AuthCallback){
        apiService.getToken(username, password).enqueue(object : Callback<AuthGsonModel> {

            override fun onResponse(call: Call<AuthGsonModel>, response: Response<AuthGsonModel>) {
                if (response.isSuccessful && response.code() == 200) {
                    saveToken("Bearer ${response.body()?.token}")
                    callback.onLoginSuccessfull()
                } else {
                    callback.onLoginFailed()
                }
            }

            override fun onFailure(call: Call<AuthGsonModel>, t: Throwable) {
                Log.e("AuthService", "Authorization failed, check logs")
                t.printStackTrace()
            }
        })
    }

    fun <T> makeRequest(call: Call<T>, callback: Callback<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 401) {
                    clearToken()
                    //TODO navigate to login
                } else {
                    callback.onResponse(call, response)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e("AuthService", "Call failed, check logs")
                t.printStackTrace()
            }
        })
    }

    private fun saveToken(token : String?) {
        sharedPreferences.edit().putString(TOKEN, token).commit()
    }

    private fun clearToken() {
        sharedPreferences.edit().remove(TOKEN).commit()
    }

    fun getAuthToken() : String {
        return sharedPreferences.getString(TOKEN, "")!!
    }
}