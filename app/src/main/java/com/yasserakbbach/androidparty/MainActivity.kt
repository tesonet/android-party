package com.yasserakbbach.androidparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.compose.rememberNavController
import com.yasserakbbach.androidparty.navigation.AndroidPartyNavGraph
import com.yasserakbbach.androidparty.ui.theme.AndroidPartyTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPartyTheme {
                val navController = rememberNavController()
                AndroidPartyNavGraph(navController = navController)
            }
        }
    }
}