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