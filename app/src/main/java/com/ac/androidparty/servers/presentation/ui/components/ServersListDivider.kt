package com.ac.androidparty.servers.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors


@Composable
internal fun ServersListItemDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.XXL)
            .height(height = Spacing.XXXS)
            .background(color = Colors.dividerGrey)
    ) {}
}