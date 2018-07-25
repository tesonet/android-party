package com.teso.net.data_flow.interactions


interface ILoginInteractor {

    fun hasUserName(): Boolean

    fun getUserName(): String

    fun setUserName(name: String)

    fun clearUserName()

    fun hasPassword(): Boolean

    fun getPassword(): String

    fun setPassword(password: String)

    fun clearPassword()

    fun getToken(): String

    fun hasToken(): Boolean

    fun clearToken()

    fun setToken(accessToken: String)

}