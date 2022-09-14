package com.ac.androidparty.login.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ac.androidparty.R
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors

@Composable
fun LoginButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth(Spacing.mainComponentsWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.primaryButton,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(R.string.login_button_text),
            color = Color.White,
            modifier = Modifier.padding(Spacing.XL)
        )
    }
}