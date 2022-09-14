package com.ac.androidparty.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors
import com.ac.androidparty.login.ui.components.LoginButton
import com.ac.androidparty.login.ui.components.LoginInputText
import com.ac.androidparty.login.ui.components.LoginLogo
import com.ac.androidparty.login.viewmodel.LoginViewModel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        modifier = modifier
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.primaryBackground)
            .verticalScroll(rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LoginLogo()
        LoginInputComponents()
    }
}

@Composable
private fun LoginInputComponents() {
    Column {
        LoginInputText(isUsername = true)
        LoginSpacer()
        LoginInputText(isUsername = false)
        LoginSpacer()
        LoginButton()
    }
}

@Composable
private fun LoginSpacer() {
    Spacer(modifier = Modifier.height(Spacing.XL))
}