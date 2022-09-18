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
import com.ac.androidparty.login.viewmodel.LoginEvent
import com.ac.androidparty.login.viewmodel.LoginViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoginRoute(
    navigateToServers: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.state.collectAsStateWithLifecycle()

    if (loginState is LoginState.Success) navigateToServers()

    LoginScreen(
        loginState = loginState,
        onUsernameChanged = viewModel::handleEvent,
        onPasswordChanged = viewModel::handleEvent
    ) { viewModel.handleEvent(LoginEvent.Login { navigateToServers() }) }
}

@Composable
private fun LoginScreen(
    loginState: LoginState,
    onUsernameChanged: LoginEvent.() -> Unit,
    onPasswordChanged: LoginEvent.() -> Unit,
    onLoginButtonClicked: () -> Unit
) {
    Scaffold { padding ->
        LoginComponents(
            paddingValues = padding,
            onUsernameChanged = onUsernameChanged,
            onPasswordChanged = onPasswordChanged,
            onLoginButtonClicked = onLoginButtonClicked,
            isUsernamePasswordWrong = loginState is LoginState.WrongCredentials
        )

        CircularProgressBarComponent(
            isDisplayed = loginState is LoginState.Loading,
            color = Colors.primaryButton,
            modifier = Modifier
                .fillMaxSize()
        )

        ErrorToastComponent(state = loginState)
    }
}

@Composable
private fun LoginComponents(
    paddingValues: PaddingValues,
    onUsernameChanged: LoginEvent.() -> Unit,
    onPasswordChanged: LoginEvent.() -> Unit,
    onLoginButtonClicked: () -> Unit,
    isUsernamePasswordWrong: Boolean = false
) {
    Box(Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
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
    onUsernameChanged: LoginEvent.() -> Unit,
    onPasswordChanged: LoginEvent.() -> Unit,
    onLoginButtonClicked: () -> Unit,
    isUsernamePasswordWrong: Boolean
) {
    Column {
        LoginInputText(
            isUsername = true,
            onValueChanged = { value ->
                onUsernameChanged(LoginEvent.UsernameChanged(value))
            },
            isErrored = isUsernamePasswordWrong
        )
        LoginSpacer()
        LoginInputText(
            isUsername = false,
            onValueChanged = { value ->
                onPasswordChanged(LoginEvent.PasswordChanged(value))
            },
            isErrored = isUsernamePasswordWrong
        )
        LoginSpacer()
        LoginButton(onLoginButtonClicked, isErrored = isUsernamePasswordWrong)
    }
}

@Composable
private fun ErrorToastComponent(
    state: LoginState
) {
    when (state) {
        is LoginState.Error -> stringResource(id = R.string.generic_network_error)
        is LoginState.WrongCredentials -> stringResource(id = R.string.login_wrong_credentials)
        else -> null
    }?.let { error ->
        Toast.makeText(
            LocalContext.current,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
private fun LoginSpacer() {
    Spacer(modifier = Modifier.height(Spacing.XL))
}