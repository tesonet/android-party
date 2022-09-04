package com.yasserakbbach.androidparty.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yasserakbbach.androidparty.R
import com.yasserakbbach.androidparty.navigation.UiEvent
import com.yasserakbbach.androidparty.ui.theme.CircleSize
import com.yasserakbbach.androidparty.ui.theme.NormalPadding
import com.yasserakbbach.androidparty.ui.theme.SmallPadding
import com.yasserakbbach.androidparty.ui.theme.SoftGreen
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collectLatest { event ->
            when(event) {
                is UiEvent.NavigateTo -> {
                    navController.popBackStack()
                    navController.navigate(event.screen.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = stringResource(R.string.login_background_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
        ) {
            TestIoTitle(
                modifier = Modifier
                    .fillMaxHeight(.3F)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.fillMaxHeight(.2F))
            TextField(
                value = state.username,
                onValueChange = { viewModel.onEvent(LoginEvent.OnUsernameChange(it)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_username),
                        contentDescription = stringResource(R.string.login_screen_usename_icon_description),
                    )
                },
                maxLines = 1,
                label = {
                    Text(text = stringResource(R.string.login_screen_usename_hint))
                },
                modifier = Modifier
                    .fillMaxWidth(.8F)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            TextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = stringResource(R.string.login_screen_password_icon_description),
                    )
                },
                maxLines = 1,
                label = {
                    Text(text = stringResource(R.string.login_screen_password_hint))
                },
                modifier = Modifier
                    .fillMaxWidth(.8F)
                    .align(Alignment.CenterHorizontally),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Button(
                onClick = { viewModel.onEvent(LoginEvent.OnLoginClick) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftGreen,
                ),
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth(.8F)
                    .align(Alignment.CenterHorizontally),
                enabled = state.isLoginEnabled,
            ) {
                Text(
                    text = stringResource(R.string.login_screen_button),
                    color = Color.White,
                    modifier = Modifier.padding(SmallPadding),
                )
            }
            Spacer(modifier = Modifier.height(SmallPadding))
            if(state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            if(state.error != null) {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Red,
                )
            }
        }
    }
}

@Composable
fun TestIoTitle(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge,
    textColor: Color = Color.White,
    circlePadding: PaddingValues = PaddingValues(bottom = NormalPadding)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.login_screen_title),
            color = textColor,
            fontWeight = FontWeight.Bold,
            style = textStyle,
            modifier = Modifier.align(Alignment.Bottom),
        )
        Circle(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(circlePadding),
        )
    }
}

@Composable
fun Circle(
    color: Color = SoftGreen,
    size: Dp = CircleSize,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        modifier = modifier.size(size),
        color = color,
    ) {

    }
}