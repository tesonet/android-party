package com.ac.androidparty.servers.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors

@Composable
internal fun ServersListItem() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ServersListItemText(text = "Canada #10")
            ServersListItemText(text = "3423 km")
        }
        ServersListItemDivider()
    }
}

@Composable
private fun ServersListItemText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.padding(
            start = Spacing.XXL,
            end = Spacing.XXL,
            top = Spacing.XXXL,
            bottom = Spacing.XXXL
        ),
        color = Colors.textGrey,
    )
}

@Composable
private fun ServersListItemDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.XXL)
            .height(height = Spacing.XXXS)
            .background(color = Colors.dividerGrey)
    ) {}
}