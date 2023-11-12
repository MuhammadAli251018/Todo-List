package online.muhammadali.todolist.feature_main.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDAO {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun addNewTask(task: Task): Long

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task")
    suspend fun clearAllTasks()

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM Task WHERE taskId LIKE :id")
    fun getTaskById(id: Long): Flow<Task>

    @Query("SELECT * FROM task WHERE parentId LIKE :parentId")
    fun getAllTasksWithCommonParent(parentId: Long): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE priority LIKE :priorityLevel")
    fun getAllTasksWithCommonPriority(priorityLevel: Int): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE completeness LIKE :completeness")
    fun getAllTasksWithCommonCompleteness(completeness: Float): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE title LIKE  '%' || :keyword || '%'")
    fun searchInTaskTitle(keyword: String): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE description LIKE  '%' || :keyword || '%'")
    fun searchInTaskDescription(keyword: String): Flow<List<Task>>

    //  todo add deleting tasks with it's subtasks
}