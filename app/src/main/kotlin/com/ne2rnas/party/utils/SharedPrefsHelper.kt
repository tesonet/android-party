package com.ne2rnas.party.utils

interface SharedPrefsHelper {

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(isLoggedIn: Boolean)

    fun getToken(): String?

    fun setToken(accessToken: String?)

}