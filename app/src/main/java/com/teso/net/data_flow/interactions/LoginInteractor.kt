package com.teso.net.data_flow.interactions

import android.text.TextUtils
import com.teso.net.data_flow.IUserStorage


class LoginInteractor(private val pref: IUserStorage) : ILoginInteractor {

    override fun hasUserName() = !TextUtils.isEmpty(getUserName())

    override fun getUserName() = pref.getUserName()

    override fun setUserName(name: String) = pref.setUserName(name)

    override fun clearUserName() = pref.setUserName("")

    override fun hasPassword() = !TextUtils.isEmpty(getPassword())

    override fun getPassword() = pref.getPassword()

    override fun setPassword(password: String) = pref.setPassword(password)

    override fun clearPassword() = pref.setPassword("")

    override fun setToken(accessToken: String) = pref.setToken(accessToken)

    override fun getToken() = pref.getToken()

    override fun hasToken() = !TextUtils.isEmpty(getToken())

    override fun clearToken() = pref.setToken("")

}