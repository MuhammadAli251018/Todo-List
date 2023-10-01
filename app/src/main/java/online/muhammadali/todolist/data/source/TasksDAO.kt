package online.muhammadali.todolist.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDAO {

    @Query("SELECT * FROM task")
    fun readAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE listId LIKE :listId ")
    fun readAllTasksInList(listId: Long): Flow<List<Task>>

    /** search for tasks content in the list*/
    @Query("SELECT * FROM task WHERE listId LIKE :listId AND taskContent LIKE '%' || :content || '%'")
    fun searchForTasksByContent(content: String, listId: Long): Flow<List<Task>>

    @Delete
    suspend fun deleteTask(taskId: Long): Result<Unit>

    @Update
    suspend fun updateTask(task: Task): Result<Unit>

    @Insert
    suspend fun insertTask(task: Task): Result<Long>
}