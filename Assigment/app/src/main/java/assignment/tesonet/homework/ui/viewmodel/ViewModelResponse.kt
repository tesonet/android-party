package assignment.tesonet.homework.ui.viewmodel

import android.arch.lifecycle.MutableLiveData

interface ViewModelResponse {
    val loginResponse: MutableLiveData<Int>
}