package com.example.androidparty.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.androidparty.db.ServerEntity
import com.example.androidparty.ui.loading.LoadingActivity
import com.example.androidparty.ui.login.LoginActivity
import com.example.androidparty.ui.server.ServerActivity
import com.example.androidparty.ui.server.ServerActivity.Companion.SERVERS_LIST
import java.io.Serializable

fun openLoginScreen(context: Context) {
    val intent = Intent(context, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    startActivity(context, intent, null)
}

fun openLoadingActivity(context: Context) {
    val intent = Intent(context, LoadingActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    startActivity(context, intent, null)
}

fun openServerListScreen(context: Context, servers: List<ServerEntity>) {
    val intent = Intent(context, ServerActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    intent.putExtra(SERVERS_LIST, servers as Serializable)
    startActivity(context, intent, null)
}