package com.example.justinaszableckisand.androidparty

import com.example.justinaszableckisand.androidparty.Models.Server

interface OnServerItemsClick {
    fun onServerClick(server : Server)
    fun onDistanceClick(server: Server)
}