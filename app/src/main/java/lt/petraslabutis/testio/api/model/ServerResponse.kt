package lt.petraslabutis.testio.api.model

import io.realm.RealmObject

open class ServerResponse(
    var name: String = "",
    var distance: Int = 0
) : RealmObject()