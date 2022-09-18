package com.ac.androidparty.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ac.androidparty.core.spacing.Spacing

@Composable
fun CircularProgressBarComponent(
    modifier: Modifier = Modifier,
    isDisplayed: Boolean,
    color: Color = Color.White,
) {
    if (isDisplayed) {
        Box() {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.fillMaxSize(0.1f))
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize(0.6f),
                    color = color,
                    strokeWidth = Spacing.M
                )
            }
        }
    }
}
