package com.example.ievazygaite.androidparty.data

import android.content.Context
import android.content.SharedPreferences
import com.example.ievazygaite.androidparty.utils.DEF_TOKEN
import com.example.ievazygaite.androidparty.utils.KEY_TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsStorage @Inject constructor(context: Context) {
    private var mPref: SharedPreferences = context.getSharedPreferences(KEY_TOKEN, Context.MODE_PRIVATE)

    fun clear() {
        mPref.edit().clear().apply()
    }

    fun addToken(searchKey: String) {
        mPref.edit().putString(KEY_TOKEN, searchKey).apply()
    }

    fun getToken(): String {
        return mPref.getString(KEY_TOKEN, DEF_TOKEN)!!
    }
}
