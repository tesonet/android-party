package com.ac.androidparty.login.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ac.androidparty.R
import com.ac.androidparty.core.components.CircularProgressBarComponent
import com.ac.androidparty.core.spacing.Spacing
import com.ac.androidparty.core.theme.Colors
import com.ac.androidparty.login.presentation.LoginState
import com.ac.androidparty.login.presentation.ui.components.LoginButton
import com.ac.androidparty.login.presentation.ui.components.LoginInputText
import com.ac.androidparty.login.presentation.ui.components.LoginLogo
import com.ac.androidparty.login.viewmodel.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        modifier = modifier,
        loginState = loginState,
        onUsernameChanged = viewModel::updateUsername,
        onPasswordChanged = viewModel::updatePassword,
        onLoginButtonClicked = viewModel::login
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier,
    loginState: LoginState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Scaffold { padding ->
        LoginComponents(
            modifier = modifier,
            paddingValues = padding,
            onUsernameChanged = onUsernameChanged,
            onPasswordChanged = onPasswordChanged,
            onLoginButtonClicked = onLoginButtonClicked,
            isUsernamePasswordWrong = loginState is LoginState.WrongCredentials
        )

        CircularProgressBarComponent(
            isDisplayed = loginState is LoginState.Loading,
            color = Colors.primaryButton
        )

        ErrorToastComponent(isDisplayed = loginState is LoginState.Error)
    }
}

@Composable
private fun LoginComponents(
    modifier: Modifier,
    paddingValues: PaddingValues,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    isUsernamePasswordWrong: Boolean = false
) {
    Box(Modifier.padding(paddingValues)) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Colors.primaryBackground)
                .verticalScroll(rememberScrollState(), enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LoginLogo()
            LoginInputComponents(
                onUsernameChanged = onUsernameChanged,
                onPasswordChanged = onPasswordChanged,
                onLoginButtonClicked = onLoginButtonClicked,
                isUsernamePasswordWrong = isUsernamePasswordWrong
            )
        }
    }
}

@Composable
private fun LoginInputComponents(
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    isUsernamePasswordWrong: Boolean
) {
    Column {
        LoginInputText(isUsername = true, onUsernameChanged, isErrored = isUsernamePasswordWrong)
        LoginSpacer()
        LoginInputText(isUsername = false, onPasswordChanged, isErrored = isUsernamePasswordWrong)
        LoginSpacer()
        LoginButton(onLoginButtonClicked, isErrored = isUsernamePasswordWrong)
    }
}

@Composable
private fun ErrorToastComponent(
    isDisplayed: Boolean
) {
    if (isDisplayed) Toast.makeText(
        LocalContext.current,
        stringResource(R.string.generic_network_error),
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
private fun LoginSpacer() {
    Spacer(modifier = Modifier.height(Spacing.XL))
}