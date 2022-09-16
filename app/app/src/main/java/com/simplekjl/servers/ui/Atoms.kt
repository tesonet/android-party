package com.simplekjl.servers.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.servers.R
import com.simplekjl.servers.R.string
import com.simplekjl.servers.ui.theme.ServersTheme


@Composable
fun NoServersMessage() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = stringResource(id = string.no_available_servers_msg),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun LoaderPreview() {
    ServersTheme {
        Loader(visibility = true)
    }
}

@Composable
fun Loader(visibility: Boolean) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .padding(top = 100.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.White
                )
                Text(
                    text = stringResource(string.fetching_list_title),
                    modifier = Modifier.padding(top = 50.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderItemPreview() {
    ServersTheme {
        Column {
            HeaderItem()
            ServerInformationPreview()
        }

    }
}

@Composable
fun HeaderItem() {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(string.server_title),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = stringResource(string.distance_title),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServerInformationPreview() {
    ServersTheme {
        ServerInformationItem(serverDetails = ServerDetails(12, "Berlin", 9))
    }
}

@Composable
fun ServerInformationItem(serverDetails: ServerDetails) {
    /*
    if (index < itemsList.lastIndex)
            Divider(color = Color.Black, thickness = 1.dp) for divider
     */
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = serverDetails.name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primaryVariant
            )
            Text(
                text = "${serverDetails.distance} km",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}


@Composable
fun InputField(
    modifier: Modifier,
    text: String,
    @StringRes hint: Int,
    @StringRes iconDescription: Int,
    @DrawableRes leadingIcon: Int?,
    isSecure: Boolean,
    onValueChange: (text: String) -> Unit,
    focusManager: FocusManager? = null,
    onKeyboardDone: (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        placeholder = @Composable {
            Text(
                text = stringResource(id = hint),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
        },
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.primary,
            backgroundColor = Color.White
        ),
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = stringResource(id = iconDescription),
                    modifier = Modifier.alpha(0.5f),
                    tint = MaterialTheme.colors.primary,
                )
            }
        },
        visualTransformation =
        if (isSecure) PasswordVisualTransformation() else
            VisualTransformation.None,
        keyboardOptions = if (isSecure) KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ) else KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onDone = { onKeyboardDone?.invoke() }) {
            focusManager?.moveFocus(FocusDirection.Down)
        }
    )
}
