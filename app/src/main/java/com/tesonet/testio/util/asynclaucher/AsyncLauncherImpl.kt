package com.tesonet.testio.util.asynclaucher

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AsyncLauncherImpl: AsyncLauncher {
    override fun launch(block: suspend () -> Unit) {
        GlobalScope.launch { block() }
    }
}