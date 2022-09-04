package com.yasserakbbach.androidparty.listsevers.presenation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.yasserakbbach.androidparty.R
import com.yasserakbbach.androidparty.listsevers.domain.model.Server
import com.yasserakbbach.androidparty.login.presentation.TestIoTitle
import com.yasserakbbach.androidparty.navigation.UiEvent
import com.yasserakbbach.androidparty.ui.theme.DarkPurple
import com.yasserakbbach.androidparty.ui.theme.Gray
import com.yasserakbbach.androidparty.ui.theme.NormalPadding
import com.yasserakbbach.androidparty.ui.theme.SmallPadding
import com.yasserakbbach.androidparty.ui.theme.SoftGray
import com.yasserakbbach.androidparty.ui.theme.SofterGray
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun ServerListScreen(
    viewModel: ServerListViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateTo -> {
                    navController.apply {
                        popBackStack()
                        navigate(event.screen.route)
                    }
                }
            }
        }

    }

    if(state.isLoading) {
        ServerListLoading()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.isRefreshing),
            onRefresh = { viewModel.onEvent(ServerListEvent.OnSwipeRefresh) },
        ) {
            ScaffoldServerList(servers = state.servers) {
                viewModel.onEvent(ServerListEvent.OnLogoutClick)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ScaffoldServerList(
    servers: List<Server>,
    onLogoutClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            ServerListTopBar {
                onLogoutClick()
            }
        },
        containerColor = SofterGray,
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
        ) {
            ServerListHeader(
                modifier = Modifier.background(color = Color.White),
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(SmallPadding),
            ) {
                items(servers) {
                    ServerListItem(serverUiModel = it.toServerUiModel())
                    Divider(
                        color = Color.Black.copy(alpha = .5F),
                        modifier = Modifier.padding(start = NormalPadding, end = NormalPadding),
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ServerListTopBar(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = SmallPadding),
    ) {
        TestIoTitle(
            textStyle = MaterialTheme.typography.displaySmall,
            textColor = DarkPurple,
            circlePadding = PaddingValues(bottom = SmallPadding),
        )
        IconButton(onClick = { onLogoutClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = stringResource(R.string.list_servers_screen_logout_description),
                tint = DarkPurple,
            )
        }
    }
}

@Composable
fun ServerListHeader(
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = SmallPadding, end = NormalPadding),
    ) {
        Text(
            text = stringResource(R.string.list_servers_screen_server_label),
            color = SoftGray,
            modifier = Modifier.padding(NormalPadding),
        )
        Text(
            text = stringResource(R.string.list_servers_screen_distance_label),
            color = SoftGray,
            modifier = Modifier.padding(NormalPadding),
        )
    }
}

@Composable
fun ServerListItem(
    modifier: Modifier = Modifier,
    serverUiModel: ServerUiModel,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = SmallPadding, end = NormalPadding),
    ) {
        Text(
            text = serverUiModel.name,
            color = Gray.copy(alpha = .5F),
            modifier = Modifier.padding(NormalPadding),
        )
        Text(
            text = serverUiModel.distance,
            color = Gray.copy(alpha = .5F),
            modifier = Modifier.padding(NormalPadding),
        )
    }
}

@Composable
fun ServerListLoading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = stringResource(R.string.login_background_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(.5F)
                    .fillMaxHeight(.5F),
            )
            Text(
                text = stringResource(R.string.list_servers_loading_text_label),
                color = Color.White,
            )
        }
    }
}