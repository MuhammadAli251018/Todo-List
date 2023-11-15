package online.muhammadali.todolist.util

import kotlinx.coroutines.runBlocking

abstract class TestExecutor {

    abstract fun setup()

    abstract suspend fun tearUp()

    open fun executeTest(test: suspend () -> Unit) {
        runBlocking {
            setup()
            test()
            tearUp()
        }
    }
}