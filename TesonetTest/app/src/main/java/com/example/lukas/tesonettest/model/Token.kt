package com.example.lukas.tesonettest.model

import lt.topocentras.android.Prefs

/**
 * Created by lukas on 17.8.17.
 */
class Token(val token: String){
	fun save() {
		Prefs.authorization = this
	}
	fun logout() {
		Prefs.authorization = null
	}
}