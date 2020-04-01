package com.assignment.android_party2.login.ui

import androidx.lifecycle.LiveData
import com.assignment.android_party2.login.models.TokenModel

interface LoginCallback {
    fun onSuccess(loginResponse: LiveData<TokenModel>)
    fun onFailure(errorMessage: String)
}