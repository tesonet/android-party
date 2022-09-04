package com.yasserakbbach.androidparty.listsevers.presenation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ServerListScreen(
    viewModel: ServerListViewModel = hiltViewModel(),
) {
    Text(text = "size: ${viewModel.servers?.size}")
}