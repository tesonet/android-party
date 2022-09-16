package com.simplekjl.servers.ui.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.servers.R
import com.simplekjl.servers.ui.InputField
import com.simplekjl.servers.ui.login.LoginState.Error
import com.simplekjl.servers.ui.login.LoginState.Loading
import com.simplekjl.servers.ui.login.LoginState.NotLoggedIn
import com.simplekjl.servers.ui.theme.ServersTheme
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun LoginScreenPreview() {
    ServersTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel = getViewModel()) {
    val state = viewModel.loginState.collectAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var visible by remember { mutableStateOf(true) }
    var btnEnabled by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    when (state.value) {
        Error -> {
            Toast.makeText(context, context.getText(R.string.error_login), Toast.LENGTH_SHORT)
                .show()
            btnEnabled = true
        }
        Loading -> {
            btnEnabled = false
        }
        NotLoggedIn -> {
            btnEnabled = true
            visible = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
            ) {
                Image(
                    modifier = Modifier
                        .paddingFromBaseline(top = 200.dp, bottom = 300.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.ic_logo_light),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.fillMaxHeight(.2F))
                InputField(
                    text = username,
                    modifier = Modifier
                        .fillMaxWidth(.8F)
                        .align(Alignment.CenterHorizontally),
                    hint = R.string.username_hint,
                    iconDescription = R.string.username_hint,
                    leadingIcon = R.drawable.ic_username,
                    isSecure = false, onValueChange = { username = it },
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.height(8.dp))
                InputField(
                    text = password,
                    modifier = Modifier
                        .fillMaxWidth(.8F)
                        .align(Alignment.CenterHorizontally),
                    hint = R.string.password_hint,
                    iconDescription = R.string.password_hint,
                    leadingIcon = R.drawable.ic_lock,
                    isSecure = true, onValueChange = { password = it },
                    focusManager = focusManager
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.signIn(username, password)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                    ),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth(.8F)
                        .align(Alignment.CenterHorizontally),
                    enabled = btnEnabled,
                ) {
                    Text(
                        text = stringResource(R.string.login_text),
                        color = Color.White,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(4.dp),
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
