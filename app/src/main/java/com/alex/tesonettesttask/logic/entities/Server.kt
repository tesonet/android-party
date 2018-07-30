package com.alex.tesonettesttask.logic.entities

import io.realm.RealmObject

open class Server : RealmObject() {
    var name: String? = null
    var distance: Int? = null
}