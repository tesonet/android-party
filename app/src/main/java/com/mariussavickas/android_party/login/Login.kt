package com.mariussavickas.android_party.login

import com.mariussavickas.android_party.persistance.User

interface Login {
    fun onLogin(user: User)
}