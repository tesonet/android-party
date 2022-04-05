package com.thescriptan.tesonetparty.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TestTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    isPassword: Boolean = false,
    leadingIcon: Int? = null,
    onValueChange: (text: String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = { Text(hint, modifier = Modifier.alpha(0.5f)) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.alpha(0.5f)
                )
            }
        },
        visualTransformation =
        if (isPassword) PasswordVisualTransformation() else
            VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier,
    )
}