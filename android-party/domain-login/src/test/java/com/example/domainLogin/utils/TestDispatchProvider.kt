package com.example.domainLogin.utils

import com.example.core.dispatcher.BaseDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProvider : BaseDispatcherProvider {
    private val testDispatcher = UnconfinedTestDispatcher()
    override fun main() = testDispatcher
    override fun io() = testDispatcher
    override fun compute() = testDispatcher
}
