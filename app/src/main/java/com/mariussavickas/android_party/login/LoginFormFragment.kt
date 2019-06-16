package com.mariussavickas.android_party.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.mariussavickas.android_party.R
import com.mariussavickas.android_party.persistance.User

class LoginFormFragment: Fragment() {
    private lateinit var loginHandler: Login
    private lateinit var etLoginUsername: EditText
    private lateinit var etLoginPassword: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etLoginUsername = view.findViewById(R.id.et_login_username)
        etLoginPassword = view.findViewById(R.id.et_login_password)

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            val user = User(
                etLoginUsername.text.toString(),
                etLoginPassword.text.toString(),
                null
            )

            loginHandler.onLogin(user)
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loginHandler = context as Login
    }
}

