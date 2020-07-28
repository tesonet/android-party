package android.example.myapplication.ServersList

import android.example.myapplication.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}