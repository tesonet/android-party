package com.yasserakbbach.androidparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yasserakbbach.androidparty.login.presentation.LoginScreen
import com.yasserakbbach.androidparty.ui.theme.AndroidPartyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPartyTheme {
                LoginScreen()
            }
        }
    }
}