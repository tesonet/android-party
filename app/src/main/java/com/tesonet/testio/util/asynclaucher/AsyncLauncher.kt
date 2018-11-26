package com.tesonet.testio.util.asynclaucher


interface AsyncLauncher {
    fun launch(block: suspend () -> Unit)
}