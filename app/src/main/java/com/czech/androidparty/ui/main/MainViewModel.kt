package com.czech.androidparty.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.androidparty.datasource.cache.AndroidPartyCache
import com.czech.androidparty.preferences.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val androidPartyCache: AndroidPartyCache
): ViewModel() {

    val isLoggedIn = runBlocking {
        sharedPrefs.isLoggedIn()
    }

    fun deleteToken() {
        viewModelScope.launch {
            sharedPrefs.deleteToken()
        }
    }

    fun deleteData() {
        viewModelScope.launch {
            androidPartyCache.deleteData()
        }
    }

}