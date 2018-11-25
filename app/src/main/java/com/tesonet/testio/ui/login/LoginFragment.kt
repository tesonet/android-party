package com.tesonet.testio.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.tesonet.testio.R
import com.tesonet.testio.base.BaseFragment
import com.tesonet.testio.base.Resource
import com.tesonet.testio.data.local.entity.Credentials
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment<LoginViewModel>(), Observer<Resource<Credentials?>> {

    private lateinit var liveCredentials: LiveData<Resource<Credentials?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveCredentials = viewModel.getCredentials()
        liveCredentials.observe(this, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener {
            viewModel.setCredentials(Credentials(username.text.toString(), password.text.toString()))
        }
    }

    override fun onChanged(resource: Resource<Credentials?>) {
        // Remove observer to not update input fields when Login button is pressed
        liveCredentials.removeObserver(this)

        val credentials = resource.data
        if (credentials != null) {
            username.setText(credentials.username)
            password.setText(credentials.password)
            password.selectAll()
        }
    }
}
