package com.example.androidParty.datalayer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRules(private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}