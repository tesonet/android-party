package com.yasserakbbach.androidparty.listsevers.presenation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yasserakbbach.androidparty.R
import com.yasserakbbach.androidparty.listsevers.domain.model.Server

@Composable
fun Server.toServerUiModel(): ServerUiModel =
    ServerUiModel(
        name = name,
        distance = stringResource(id = R.string.list_servers_screen_distance_format, distance)
    )