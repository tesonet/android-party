package com.czech.androidparty.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.androidparty.preferences.SharedPrefs
import com.czech.androidparty.repositories.ListRepository
import com.czech.androidparty.responseStates.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository,
    sharedPrefs: SharedPrefs
) : ViewModel() {

    val listState = MutableStateFlow<ListState?>(null)

    private val token = sharedPrefs.fetchToken().toString()

    fun getDataWithNetwork() {
        viewModelScope.launch {
            listRepository.getFromNetwork(token).collect {
                when {
                    it.isLoading -> {
                        listState.value = ListState.Loading
                    }
                    it.data == null -> {
                        listState.value = ListState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { data ->
                            listState.value = ListState.Success(data = data)
                        }
                    }
                }
            }
        }
    }

    fun getDataFromDB() {
        viewModelScope.launch {
            listRepository.getFromDatabase().collect {
                when {
                    it.isLoading -> {
                        listState.value = ListState.Loading
                    }
                    it.data == null -> {
                        listState.value = ListState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { data ->
                            listState.value = ListState.Success(data = data)
                        }
                    }
                }
            }
        }
    }
}