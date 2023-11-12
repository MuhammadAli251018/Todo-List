package online.muhammadali.todolist.common

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import online.muhammadali.todolist.util.TestExecutor
import org.junit.Test


class ErrorTest : TestExecutor() {
    override fun setup() {
        TODO("Not yet implemented")
    }

    override suspend fun tearUp() {
        TODO("Not yet implemented")
    }

    @Test
    fun `success if catch error & return failed` () {
        var a: Int? = null
        val b = Error.getResultDbOperation {
            a = a!! + 1
        }

        assertThat(b is Failure<Unit>).isTrue()
    }

    @Test
    fun `success if return success` () {
        var a = 5
        val b = Error.getResultDbOperation {
            a ++
        }

        assertThat(b is Success<Int>).isTrue()
    }

    @Test
    fun `success if return` () = runBlocking {
        var b = 0
        val a = suspend {
            b ++
        }

        val result = Error.getResultDbOperationAsync {
            a()
        }.first()

        assertThat(result is Success<Int>).isTrue()
    }

}