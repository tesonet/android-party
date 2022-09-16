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
import com.ac.androidparty.servers.presentation.ui.components.ServersListHeader
import com.ac.androidparty.servers.presentation.ui.components.ServersListTopBar
import com.ac.androidparty.servers.viewmodel.ServersListViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun ServersListRoute(
    viewModel: ServersListViewModel = hiltViewModel()
) {
    val serversListState by viewModel.state.collectAsStateWithLifecycle()
    ServersListScreen()
}

@Composable
private fun ServersListScreen(

) {
    Scaffold(
        topBar = { ServersListTopBar() },
        backgroundColor = Colors.lightGrey
    ) { _ ->
        Column(modifier = Modifier.fillMaxWidth()) {
            ServersListHeader()
            ServersListComponents()
        }

        CircularProgressBarComponent(isDisplayed = false)
    }
}
