package online.muhammadali.todolist.feature_main.data.repository

import kotlinx.coroutines.flow.Flow
import online.muhammadali.todolist.feature_main.data.source.Task
import online.muhammadali.todolist.common.Result

interface TaskDbRepo {

    fun getAllTasks(): Flow<Result<List<Task>>>

    fun getTaskById(id: Long): Flow<Result<Task>>

    fun updateTask(task: Task): Flow<Result<Unit>>

    fun getAllSubtasks(parentId: Long): Flow<Result<List<Task>>>

    fun deleteTask(task: Task): Flow<Result<Unit>>

    fun createTask(task: Task): Flow<Result<Long>>

    fun getTasksWithPriority(priority: Int): Flow<Result<List<Task>>>

    fun getTasksWithCompleteness(completeness: Float): Flow<Result<List<Task>>>

    fun searchInTasksTitle(keyword: String): Flow<Result<List<Task>>>

    fun searchInTaskDescription(keyword: String): Flow<Result<List<Task>>>
}