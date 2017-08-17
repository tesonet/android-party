package com.example.lukas.tesonettest.model

import io.reactivex.Observable
import lt.topocentras.android.api.Api

/**
 * Created by lukas on 17.8.17.
 */
class Server(val name: String, val distance: Long) {
	companion object {
		fun getServers(): Observable<List<Server>> {
			return Api.appService.getServers()
		}
	}

}