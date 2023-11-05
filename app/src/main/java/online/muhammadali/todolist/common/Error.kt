package online.muhammadali.todolist.common

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object Error {

    fun <D>getResultDbOperation(operation: () -> D): Result<D> {
        return try {
            Result.success(operation())
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun <D> getResultDbOperation(
        operation: suspend () -> D,
        scopeContext: CoroutineContext = Dispatchers.IO
    ): Flow<Result<D>> {

        val flow = MutableSharedFlow<Result<D>>()

        CoroutineScope(scopeContext).launch {
            val handler = CoroutineExceptionHandler { coroutineContext, throwable ->

                coroutineContext.cancel()

                launch {
                    flow.emit(Result.failure(throwable))
                }
            }

            launch(scopeContext + handler) {
                async {
                    operation()
                }.await()
            }
        }

        return flow
    }
}

