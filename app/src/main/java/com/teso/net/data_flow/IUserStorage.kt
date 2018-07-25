package com.teso.net.data_flow


interface IUserStorage {
    fun getToken(): String
    fun setToken(token: String)
    fun getUserName(): String
    fun setUserName(name: String)
    fun getPassword(): String
    fun setPassword(password: String)
}