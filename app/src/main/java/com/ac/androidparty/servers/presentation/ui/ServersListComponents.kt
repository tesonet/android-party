package com.ac.androidparty.servers.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R
import com.ac.androidparty.servers.domain.model.Server
import com.ac.androidparty.servers.presentation.ServersListState
import com.ac.androidparty.servers.presentation.ui.components.ServersListItem

@Composable
internal fun ServersListComponents(
    state: ServersListState
) {
    val items: List<Server>? = when (state) {
        is ServersListState.Success -> state.servers
        is ServersListState.Error -> state.servers.takeIf { list -> list.isNotEmpty() }
            ?.let { state.servers }
        is ServersListState.Loading -> emptyList()
        else -> null
    }

    when {
        !items.isNullOrEmpty() -> {
            LazyColumn() {
                items(items = items) { item ->
                    ServersListItem(server = item)
                }
            }
        }
        items != null && items.isEmpty() -> Unit
        else -> ErrorListComponent()
    }
}

@Composable
private fun ErrorListComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            stringResource(R.string.generic_network_error),
        )
    }
}