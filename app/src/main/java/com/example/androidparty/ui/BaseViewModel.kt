package com.example.androidparty.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.androidparty.dao.AppDatabase

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val databaseInstance: AppDatabase = AppDatabase.getDatabase(application)
}