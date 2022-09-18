package com.ac.androidparty.servers.presentation.ui

import androidx.activity.compose.BackHandler
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
import com.ac.androidparty.servers.presentation.ui.components.ServersListErrorComponent
import com.ac.androidparty.servers.presentation.ui.components.ServersListHeader
import com.ac.androidparty.servers.presentation.ui.components.ServersListTopBar
import com.ac.androidparty.servers.viewmodel.ServersListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ServersListRoute(
    navigateToLogin: () -> Unit,
    onBackPressed: () -> Unit,
    viewModel: ServersListViewModel = hiltViewModel()
) {
    BackHandler { onBackPressed() }
    val serversListState by viewModel.state.collectAsStateWithLifecycle()
    if (!viewModel.isLoggedIn.value) navigateToLogin()
    ServersListScreen(
        state = serversListState,
        onRefresh = viewModel::refreshServers,
        onLogout = viewModel::logout
    )
}

@Composable
private fun ServersListScreen(
    state: ServersListState,
    onRefresh: () -> Unit,
    onLogout: () -> Unit
) {
    val isRefreshing = rememberSwipeRefreshState(isRefreshing = state is ServersListState.Loading)
    Scaffold(
        topBar = { ServersListTopBar(onLogout = onLogout) },
        backgroundColor = Colors.lightGrey
    ) { _ ->
        SwipeRefresh(
            state = isRefreshing,
            onRefresh = { onRefresh() },
            swipeEnabled = true
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ServersListHeader(isVisible = state != ServersListState.Error())
                ServersListErrorComponent(isVisible = state is ServersListState.Error && state.servers.isNotEmpty())
                ServersListComponents(state = state)
            }

        }
        CircularProgressBarComponent(isDisplayed = state is ServersListState.Loading)
    }
}
