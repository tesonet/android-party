package android.example.myapplication.ServersList.state

import android.example.myapplication.model.Server

//The goal of this class is to have a single mutable or mediator live data object for the servers list view
//Packaging every object, every data model that the view consists of inside the list state

data class ServersListState (
    var servers: List<Server> ?= null
)