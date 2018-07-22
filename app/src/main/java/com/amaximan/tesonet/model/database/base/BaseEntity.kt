package com.amaximan.tesonet.model.database.base

import android.arch.persistence.room.PrimaryKey

abstract class BaseEntity(@PrimaryKey(autoGenerate = true) var id: Int = 0)