package com.tesonet.testio.util.asynclauncher

import com.tesonet.testio.util.asynclaucher.AsyncLauncher
import kotlinx.coroutines.runBlocking


class BlockingAsyncLauncher: AsyncLauncher {
    override fun launch(block: suspend () -> Unit) {
        runBlocking { block() }
    }
}