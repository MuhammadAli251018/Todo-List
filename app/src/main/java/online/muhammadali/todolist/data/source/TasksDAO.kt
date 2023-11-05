package online.muhammadali.todolist.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDAO {
    fun getAllTasks(): Flow<List<Task>>

    suspend fun addNewTask(task: Task): Long

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)

    fun getTaskById(id: Long): Flow<Task>

    fun getAllTasksWithCommonParent(parentId: Long): Flow<List<Task>>

    fun getAllTasksWithCommonPriority(priorityLevel: Int): Flow<List<Task>>

    fun getAllTasksWithCommonCompleteness(completeness: Float): Flow<List<Task>>

    fun getAllNonCompletedTasks(): Flow<List<Task>>

    fun searchInTaskTitle(keyWord: String): Flow<List<Task>>

    fun searchInTaskDescription(keyWord: String): Flow<List<Task>>
}