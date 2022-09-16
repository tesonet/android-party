package com.simplekjl.servers.ui.list

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.simplekjl.servers.R
import com.simplekjl.servers.R.string
import com.simplekjl.servers.ui.HeaderItem
import com.simplekjl.servers.ui.Loader
import com.simplekjl.servers.ui.NoServersMessage
import com.simplekjl.servers.ui.ServerInformationItem
import com.simplekjl.servers.ui.list.ServerListState.Error
import com.simplekjl.servers.ui.list.ServerListState.FetchingData
import com.simplekjl.servers.ui.list.ServerListState.LoadData
import com.simplekjl.servers.ui.list.ServerListState.Logout
import com.simplekjl.servers.ui.theme.ServersTheme
import org.koin.androidx.compose.getViewModel

@Composable
@Preview
fun ServerListScreenPreview() {
    ServersTheme {
        ServerListScreen()
    }
}

@Composable
fun ServerListScreen(viewModel: ServerListViewModel = getViewModel()) {
    val context = LocalContext.current
    val state = viewModel.serverListState.collectAsState()
    var loaderVisibility by remember { mutableStateOf(true) }
    var listVisibility by remember { mutableStateOf(false) }

    when (state.value) {
        FetchingData -> {
            loaderVisibility = true
            listVisibility = false
        }
         LoadData -> {
            loaderVisibility = false
            listVisibility = true
        }
        Error -> {
            Toast.makeText(
                context,
                context.getText(string.something_went_wrong_error),
                Toast.LENGTH_SHORT
            )
                .show()
        }
        Logout -> {
            listVisibility = false
            loaderVisibility = false
        }
    }
    Loader(loaderVisibility)
    AnimatedVisibility(
        visible = listVisibility,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        ServerList(viewModel)
    }
}

@Composable
fun ServerList(viewModel: ServerListViewModel) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainToolbar(viewModel = viewModel)
        },
        content = { padding ->
            val serverList by viewModel.serverList.collectAsState()
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            Column {
                HeaderItem()
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                    onRefresh = { viewModel.swipeRefresh() }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            if (serverList.isEmpty() && !isRefreshing)
                                NoServersMessage()
                        }
                        itemsIndexed(serverList) { index, serverInfo ->
                            ServerInformationItem(serverInfo)
                            if (index < serverList.lastIndex)
                                Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
                        }
                    }
                }
            }
        })
}

@Composable
fun MainToolbar(viewModel: ServerListViewModel) = TopAppBar(
    title = {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo_dark),
            contentDescription = stringResource(string.server_list_description),
            tint = Color.Unspecified
        )
    },
    actions = {
        IconButton(onClick = { viewModel.logout() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                stringResource(string.log_out_description),
                tint = Color.Unspecified
            )
        }
    },
    backgroundColor = MaterialTheme.colors.background,
    elevation = 0.dp
)