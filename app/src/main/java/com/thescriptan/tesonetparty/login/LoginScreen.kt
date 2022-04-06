package com.thescriptan.tesonetparty.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thescriptan.tesonetparty.R
import com.thescriptan.tesonetparty.components.TesoTextField
import com.thescriptan.tesonetparty.login.model.LoginRequest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val loginState = viewModel.loginState.collectAsState().value
    val loadingVisibility = viewModel.loadingVisibility.collectAsState().value
    val idleVisibility = viewModel.idleVisibility.collectAsState().value

    LaunchedEffect("errorMessage") {
        viewModel.errorMessage.onEach {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }.launchIn(this)
    }

    //TODO: Better add onboarding screen with CircularProgressIndicator and check loggedIn status
    viewModel.isLoggedIn()
    viewModel.handleVisibility(loginState)

    LoginBackground()
    LoginIdle(viewModel, idleVisibility)
    LoginLoading(loadingVisibility)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginIdle(viewModel: LoginViewModel, visibility: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginLogo(modifier = Modifier.paddingFromBaseline(bottom = 80.dp))
            LoginInteractables { loginRequest ->
                if (visibility) {
                    viewModel.login(loginRequest)
                    keyboardController?.hide()
                }
            }
        }
    }
}

@Composable
private fun LoginLoading(visibility: Boolean) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(0.5f),
                color = Color.White
            )
            Text(
                text = "Fetching the list...",
                modifier = Modifier.padding(top = 50.dp),
                color = Color.White,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
private fun LoginInteractables(onLoginPressed: (loginRequest: LoginRequest) -> Unit) {
    val focusManager = LocalFocusManager.current
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 64.dp)
    ) {
        TesoTextField(
            text = username,
            hint = stringResource(R.string.username),
            leadingIcon = R.drawable.ic_username,
            onValueChange = { username = it },
            focusManager = focusManager
        )
        Spacer(Modifier.size(18.dp))
        TesoTextField(
            text = password,
            hint = stringResource(R.string.password),
            isPassword = true,
            leadingIcon = R.drawable.ic_lock,
            onValueChange = { password = it },
            focusManager = focusManager,
            onKeyboardDone = { onLoginPressed(LoginRequest(username, password)) }
        )
        Spacer(Modifier.size(18.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(159, 213, 51),
                contentColor = Color.White
            ),
            onClick = { onLoginPressed(LoginRequest(username, password)) }
        ) {
            Text(text = "Login", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
private fun LoginBackground(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun LoginLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.logo_testio),
        contentDescription = null,
    )
}
