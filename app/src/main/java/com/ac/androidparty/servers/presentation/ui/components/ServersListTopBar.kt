package com.ac.androidparty.servers.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors

@Composable
internal fun ServersListTopBar(
    isVisible: Boolean = true,
    onLogout: () -> Unit
) {
    if (isVisible) {
        Row(
            modifier = Modifier
                .background(Colors.lightGrey)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            ServersListIcon(
                iconId = R.drawable.severs_screen_logo,
                contentDescription = R.string.servers_list_logo_content_description
            )
            ServersListLogoutButton(
                onLogoutClicked = { onLogout() },
                iconId = R.drawable.ic_ico_logout,
                contentDescription = R.string.servers_list_logout_content_description
            )
        }
    }
}

@Composable
private fun ServersListIcon(
    @DrawableRes iconId: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription = stringResource(contentDescription),
        modifier = modifier
            .padding(
                start = Spacing.XXL,
                end = Spacing.XXL,
                top = Spacing.huge,
                bottom = Spacing.XL
            )
            .height(Spacing.topAppBarHeight)
    )
}

@Composable
private fun ServersListLogoutButton(
    onLogoutClicked: () -> Unit,
    @DrawableRes iconId: Int,
    @StringRes contentDescription: Int
) {
    ServersListIcon(
        iconId = iconId,
        contentDescription = contentDescription,
        modifier = Modifier.clickable {
            onLogoutClicked()
        }
    )
}
