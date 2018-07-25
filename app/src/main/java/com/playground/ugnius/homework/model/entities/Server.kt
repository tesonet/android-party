package com.playground.ugnius.homework.model.entities

import io.realm.RealmObject

open class Server() : RealmObject() {

    open var name: String? = null
    open var distance: Int? = null

    //used for realm persistence
    constructor(name: String, distance: Int) : this() {
        this.name = name
        this.distance = distance
    }
}