package com.example.justinaszableckisand.androidparty.Login

import com.example.justinaszableckisand.androidparty.Base.BaseView

interface LoginContract {
    interface View : BaseView<Presenter>{
        fun onSuccess(token : String)
    }
    interface Presenter{
        fun logIn (username : String, password : String)
    }
}