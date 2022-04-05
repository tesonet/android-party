package com.thescriptan.tesonetparty.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thescriptan.tesonetparty.R
import com.thescriptan.tesonetparty.list.model.ServerInfo

@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListTopBar(viewModel = viewModel)
        }
    ) {
        ListServer(serverList = viewModel.getServerList())
    }
}

@Composable
fun ListTopBar(viewModel: ListViewModel) = TopAppBar(
    title = {
        Icon(
            painter = painterResource(id = R.drawable.logo_testio_black),
            contentDescription = "topBarLogo",
            tint = Color.Unspecified
        )
    },
    actions = {
        IconButton(onClick = { viewModel.logout() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                "backIcon",
                tint = Color.Unspecified
            )
        }
    },
    backgroundColor = Color(240, 240, 240)
)

@Composable
fun ListServer(serverList: List<ServerInfo>) {
    LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)) {
        item {
            ListServerHeader()
        }
        items(serverList) { serverInfo ->
            ListServerViewHolder(serverInfo)
        }
    }
}

@Composable
fun ListServerHeader() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "SERVER", style = MaterialTheme.typography.body1)
        Text(text = "DISTANCE", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun ListServerViewHolder(serverInfo: ServerInfo) {

    @Composable
    fun ViewHolderText(text: String) = Text(
        text = text, color = Color(102, 102, 102),
        style = MaterialTheme.typography.body1
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ViewHolderText(text = serverInfo.name)
            ViewHolderText(text = serverInfo.distance.toString())
        }
        Divider()
    }
}
