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

    fun <D>getResultDbOperation (operation: () -> D): Result<D> {
        return try {
            Result.success(operation())
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun <D> getResultDbOperationAsync (
        scopeContext: CoroutineContext = Dispatchers.IO,
        operation: suspend () -> D,
    ): Flow<Result<D>> {

        val flow = MutableSharedFlow<Result<D>>()

        CoroutineScope (scopeContext).launch {
            val handler = CoroutineExceptionHandler { coroutineContext, throwable ->

                coroutineContext.cancel()

                launch {
                    flow.emit(Result.failure(throwable))
                }
            }

            launch (scopeContext + handler) {
                flow.emit(Success(async {
                    operation()
                }.await()))
            }
        }

        return flow
    }
}