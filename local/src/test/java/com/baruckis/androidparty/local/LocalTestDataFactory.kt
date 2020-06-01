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

package com.baruckis.androidparty.local

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.local.model.ServerLocal

object LocalTestDataFactory {

    private const val SERVER_NAME = "Lithuania #85"
    private const val SERVER_DISTANCE = 1407

    fun createServerLocal(): ServerLocal {
        return ServerLocal(serverId = 1, name = SERVER_NAME, distance = SERVER_DISTANCE)
    }

    fun createServerData(): ServerData {
        return ServerData(SERVER_NAME, SERVER_DISTANCE)
    }

}