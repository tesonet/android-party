package com.czech.androidparty.responseStates

import com.czech.androidparty.models.DataList

sealed class ListState {
    data class Success(val data: List<DataList>?): ListState()
    data class Error(val message: String): ListState()
    object Loading: ListState()
}
