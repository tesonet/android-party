package com.thescriptan.tesonetparty.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TesoTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    isPassword: Boolean = false,
    leadingIcon: Int? = null,
    onValueChange: (text: String) -> Unit,
    focusManager: FocusManager? = null,
    onKeyboardDone: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(
                hint,
                modifier = Modifier.alpha(0.5f),
                style = MaterialTheme.typography.body1
            )
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.alpha(0.5f),
                    tint = Color.Unspecified,
                )
            }
        },
        visualTransformation =
        if (isPassword) PasswordVisualTransformation() else
            VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ) else KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onDone = { onKeyboardDone?.invoke() }) {
            focusManager?.moveFocus(FocusDirection.Down)
        },
        modifier = modifier,
    )
}