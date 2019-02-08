package com.example.justinaszableckisand.androidparty.Servers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.justinaszableckisand.androidparty.Constants
import com.example.justinaszableckisand.androidparty.Login.LoginActivity
import com.example.justinaszableckisand.androidparty.Models.Server
import com.example.justinaszableckisand.androidparty.R
import com.example.justinaszableckisand.androidparty.Utils.ServersAdapter
import com.irozon.sneaker.Sneaker
import es.dmoral.prefs.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ServersContract.View {

    lateinit var mPresenter : ServersContract.Presenter
    lateinit var mToken : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val serverPResenter = ServersPresenter(this,this)
        mToken = Prefs.with(this).read(Constants.TOKEN_PREFS,"")
        rcServers.layoutManager = LinearLayoutManager(this)
        setupView()
        setupListeners()
    }

    private fun setupListeners() {
        ivLogOut.setOnClickListener {
            Prefs.with(baseContext).write(Constants.TOKEN_PREFS,"")
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun setupView() {
        if(mToken != ""){
            mPresenter.getServers(mToken)
        } else startActivity(Intent(this,LoginActivity::class.java))
    }

    override fun onSuccess(serversList: List<Server>) {
        rcServers.adapter = ServersAdapter(this,serversList)
    }

    override fun setPresenter(presenter: ServersContract.Presenter?) {
        mPresenter = presenter!!
    }

    override fun onError(errorResourceId: Int) {
        when (errorResourceId) {
            401 -> {
                startActivity(Intent(this,LoginActivity::class.java))
            }
            404 -> {
                Sneaker.with(this).setTitle("Server error").sneakError()
            }
        }
    }

    override fun onError(errorMessage: String?) {
        Sneaker.with(this).setTitle(errorMessage).sneakError()
    }
}
