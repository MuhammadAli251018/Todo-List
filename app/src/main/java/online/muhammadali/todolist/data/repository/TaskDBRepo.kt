package online.muhammadali.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import online.muhammadali.todolist.data.source.Task

interface TaskDBRepo {

    fun getAllTasks(): Flow<List<Task>>

    fun getAllTasksInList(listId: Long): Flow<List<Task>>

    /** search for tasks content in the list*/
    fun getTasksByContent(content: String, listId: Long): Flow<List<Task>>

    suspend fun deleteTask(taskId: Long): Result<Unit>

    suspend fun updateTask(task: Task): Result<Unit>

    suspend fun setTaskAsCompleted(task: Task): Result<Unit>

    suspend fun addTask(task: Task): Result<Long>
}