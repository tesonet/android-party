package com.amaximan.tesonet.model.sp

import android.annotation.SuppressLint
import android.content.Context
import com.amaximan.tesonet.App

class SPManager private constructor() {
    private val spName = "sp_name"
    private val spTokenKey = "sp_token"

    object Holder {
        val INSTANCE = SPManager()
    }

    companion object {
        val instance: SPManager by lazy { Holder.INSTANCE }
    }

    fun setToken(token: String) {
        App.instance.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(spTokenKey, token).apply()
    }

    @SuppressLint("ApplySharedPref")
    fun deleteToken() {
        App.instance.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().remove(spTokenKey).commit()
    }

    fun getToken(): String? = App.instance.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(spTokenKey, null)
}