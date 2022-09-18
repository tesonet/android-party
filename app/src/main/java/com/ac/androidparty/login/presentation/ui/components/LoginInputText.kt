package com.ac.androidparty.login.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ac.androidparty.R
import com.ac.androidparty.core.spacing.Spacing

@Composable
internal fun LoginInputText(
    isUsername: Boolean,
    onValueChanged: (String) -> Unit,
    isErrored: Boolean
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        label = { InputTextLabel(isUsername = isUsername) },
        value = text,
        leadingIcon = { InputTextLeadingIcon(isUsername = isUsername, isError = isErrored) },
        onValueChange = { value ->
            text = value
            onValueChanged(value)
        },
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .clip(RoundedCornerShape(Spacing.roundedCorners)),
        visualTransformation = if (isUsername.not())
            PasswordVisualTransformation(mask = PASSWORD_CHAR) else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            errorLabelColor = Color.Red,
            textColor = if (isErrored) Color.Red else Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        isError = isErrored
    )
}

@Composable
private fun InputTextLabel(
    isUsername: Boolean
) {
    val defaultText =
        if (isUsername) stringResource(R.string.login_input_username_text) else
            stringResource(R.string.login_input_password_text)
    Text(text = defaultText)
}

@Composable
private fun InputTextLeadingIcon(
    isUsername: Boolean,
    isError: Boolean
) {
    val icon = if (isUsername) R.drawable.ic_ico_username else R.drawable.ic_ico_lock
    Icon(
        painter = painterResource(icon),
        contentDescription = stringResource(R.string.login_input_icon_description),
        tint = if (isError) Color.Red else Color.Gray
    )
}

const val PASSWORD_CHAR = '*'