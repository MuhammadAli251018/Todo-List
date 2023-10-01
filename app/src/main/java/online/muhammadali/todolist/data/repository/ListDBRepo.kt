package online.muhammadali.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import online.muhammadali.todolist.data.source.TasksList

interface ListDBRepo {

    fun getAllLists(): Flow<List<TasksList>>

    fun getListById(): Flow<List<TasksList>>

    suspend fun deleteList(): Result<Boolean>

    suspend fun addList(list: TasksList): Result<Long>

    suspend fun updateList(list: TasksList): Result<Long>
}