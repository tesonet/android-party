package com.thescriptan.tesonetparty.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thescriptan.tesonetparty.R
import com.thescriptan.tesonetparty.components.TestTextField
import com.thescriptan.tesonetparty.login.model.LoginRequest

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val loginState = viewModel.loginState.collectAsState().value

    LoginBackground()
    when (loginState) {
        LoginState.Idle -> LoginIdle(viewModel)
        LoginState.Loading -> LoginLoading()
        LoginState.Authorized -> viewModel.navigateToList()
        is LoginState.Error -> {
            Toast.makeText(context, "Error: ${loginState.message}", Toast.LENGTH_SHORT).show()
            LoginIdle(viewModel)
        }
    }
}

@Composable
private fun LoginIdle(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginLogo(modifier = Modifier.paddingFromBaseline(bottom = 80.dp))
        LoginInteractables { loginRequest ->
            viewModel.login(loginRequest)
        }
    }
}

@Composable
private fun LoginLoading() {
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

@Composable
private fun LoginInteractables(onLoginPressed: (loginRequest: LoginRequest) -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 64.dp)
    ) {
        TestTextField(
            text = username,
            hint = stringResource(R.string.username),
            leadingIcon = R.drawable.ic_username,
            onValueChange = { username = it })
        Spacer(Modifier.size(18.dp))
        TestTextField(
            text = password,
            hint = stringResource(R.string.password),
            isPassword = true,
            leadingIcon = R.drawable.ic_lock,
            onValueChange = { password = it })
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
