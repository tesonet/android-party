package com.ac.androidparty.login.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R

@Composable
fun LoginLogo(
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logotype_testio_),
            contentDescription = stringResource(R.string.login_logo_description),
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}