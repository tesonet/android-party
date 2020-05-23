package com.baruckis.androidparty.presentation.main

import androidx.lifecycle.ViewModel
import com.baruckis.androidparty.domain.usecases.LogoutUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logoutClick() {
        logoutUseCase.execute()
    }

}