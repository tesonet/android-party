package com.ac.androidparty.servers.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors
import java.util.*

@Composable
internal fun ServersListHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ServersListHeaderText(textId = R.string.servers_list_server_text)
        ServersListHeaderText(textId = R.string.servers_list_distance_text)
    }
}

@Composable
private fun ServersListHeaderText(
    @StringRes textId: Int
) {
    Text(
        text = stringResource(id = textId).uppercase(Locale.getDefault()),
        modifier = Modifier.padding(
            start = Spacing.XXL,
            end = Spacing.XXL,
            top = Spacing.XXXL,
            bottom = Spacing.XXXL
        ),
        color = Colors.darkGrey,
    )
}