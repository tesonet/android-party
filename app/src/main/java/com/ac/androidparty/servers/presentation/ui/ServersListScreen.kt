package com.ac.androidparty.servers.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ac.androidparty.core.components.CircularProgressBarComponent
import com.ac.androidparty.core.theme.Colors
import com.ac.androidparty.servers.presentation.ServersListState
import com.ac.androidparty.servers.presentation.ui.components.ServersListHeader
import com.ac.androidparty.servers.presentation.ui.components.ServersListTopBar
import com.ac.androidparty.servers.viewmodel.ServersListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ServersListRoute(
    viewModel: ServersListViewModel = hiltViewModel()
) {
    val serversListState by viewModel.state.collectAsStateWithLifecycle()
    ServersListScreen(state = serversListState, onRefresh = viewModel::refreshServers)
}

@Composable
private fun ServersListScreen(
    state: ServersListState,
    onRefresh: () -> Unit
) {

    val isRefreshing = rememberSwipeRefreshState(isRefreshing = state is ServersListState.Loading)

    Scaffold(
        topBar = { ServersListTopBar(isVisible = true) },
        backgroundColor = Colors.lightGrey
    ) { _ ->
        SwipeRefresh(
            state = isRefreshing,
            onRefresh = { onRefresh() },
            swipeEnabled = true
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ServersListHeader(isVisible = state != ServersListState.Error())
                ServersListComponents(state = state)
            }

        }
        CircularProgressBarComponent(isDisplayed = state is ServersListState.Loading)
    }
}
