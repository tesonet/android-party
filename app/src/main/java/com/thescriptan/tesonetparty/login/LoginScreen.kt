package com.thescriptan.tesonetparty.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thescriptan.tesonetparty.R
import com.thescriptan.tesonetparty.components.TestTextField

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    var isLoading by rememberSaveable { mutableStateOf(false) }

    LoginBackground()
    if (isLoading) {
        LoginLoading()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginLogo(modifier = Modifier.paddingFromBaseline(bottom = 80.dp))
            LoginInteractables {
                //isLoading = true
                viewModel.navigateToList()
            }
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
            color = Color.White
        )
    }
}

@Composable
private fun LoginInteractables(onLoginPressed: () -> Unit) {
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
            onClick = onLoginPressed
        ) {
            Text(text = "Login")
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

//@Preview
//@Composable
//fun PreviewLoginScreen() {
//    LoginScreen()
//}
