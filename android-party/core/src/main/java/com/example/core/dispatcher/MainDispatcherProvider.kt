package com.example.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainDispatcherProvider : BaseDispatcherProvider {
    override fun main(): CoroutineDispatcher = Dispatchers.Main
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun compute(): CoroutineDispatcher = Dispatchers.Default
}
