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

package com.baruckis.androidparty.presentation.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.usecases.GetLoggedInUserUseCase
import com.baruckis.androidparty.presentation.Event
import javax.inject.Inject

class LauncherViewModel @Inject constructor(getLoggedInUserUseCase: GetLoggedInUserUseCase) : ViewModel() {

    private val launchDestinationMutableLiveData = MutableLiveData<Event<LaunchDestination>>()

    val launchDestinationLiveData: LiveData<Event<LaunchDestination>>
        get() = launchDestinationMutableLiveData

    init {
        // Check if user has already been logged in before and then navigate the user accordingly.
        val loggedInUser = getLoggedInUserUseCase.execute()
        if (loggedInUser != null) {
            launchDestinationMutableLiveData.postValue(Event(LaunchDestination.MAIN_ACTIVITY))
        } else {
            launchDestinationMutableLiveData.postValue(Event(LaunchDestination.LOGIN))
        }
    }
}

enum class LaunchDestination {
    LOGIN,
    MAIN_ACTIVITY
}