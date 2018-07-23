package com.njakawaii.tesonet.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.njakawaii.tesonet.App
import com.njakawaii.tesonet.R
import com.njakawaii.tesonet.data.ServerModel
import com.njakawaii.tesonet.login.BaseActivity
import com.njakawaii.tesonet.login.LoginActivity
import com.orhanobut.hawk.Hawk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var adapter = MainAdapter(ArrayList()) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        serverList.adapter = adapter
        serverList.setHasFixedSize(true)
        loadDataFromDB()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Hawk.deleteAll();
        App.getInstance().database.serverDao().deleteAll()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun openMainActivity() {
        runOnUiThread {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun loadDataFromDB(){
        App.getInstance().database.serverDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (!it.isEmpty()) {
                        adapter.list = it as ArrayList<ServerModel>
                        adapter.notifyDataSetChanged()
                    } else {
                        openMainActivity()
                    }}
    }
}