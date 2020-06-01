/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.local.mapper

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.local.model.ServerLocal
import javax.inject.Inject

class LocalServerMapper @Inject constructor() : LocalMapper<ServerLocal, ServerData> {

    override fun mapFromLocal(localModel: ServerLocal): ServerData {
        return ServerData(localModel.name, localModel.distance)
    }

    override fun mapToLocal(dataModel: ServerData): ServerLocal {
        return ServerLocal(name = dataModel.name, distance = dataModel.distance)
    }

}