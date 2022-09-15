package com.ac.androidparty.servers.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ac.androidparty.servers.presentation.ui.components.ServersListItem

@Composable
internal fun ServersListComponents() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {
        ServersListItem()
        ServersListItem()
    }
}