package com.ac.androidparty.servers.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R
import com.ac.androidparty.core.spacing.Spacing

@Composable
internal fun ServersListErrorComponent(isVisible: Boolean) {
    if (isVisible) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.servers_list_not_latest_data),
                    modifier = Modifier.padding(
                        start = Spacing.XXL,
                        end = Spacing.XXL,
                        top = Spacing.XXXL,
                        bottom = Spacing.XXXL
                    ),
                    color = Color.Red,
                )
            }
            ServersListItemDivider()
        }
    }
}