package online.muhammadali.todolist.util

import kotlinx.coroutines.test.runTest

abstract class TestExecutor {

    abstract fun setup()

    abstract suspend fun tearUp()

    open fun executeTest(test: suspend () -> Unit) {
        runTest {
            setup()
            test()
            tearUp()
        }
    }
}