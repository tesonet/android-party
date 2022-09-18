package com.ac.androidparty.ui

import androidx.compose.runtime.Composable
import com.ac.androidparty.navigation.AndroidPartyAppNavHost

@Composable
fun AndroidPartyApp(
    appState: AndroidPartyAppState = rememberAndroidPartyAppState()
) {
    AndroidPartyAppNavHost(
        navController = appState.navController,
        onNavigateToDestination = appState::navigate
    )
}