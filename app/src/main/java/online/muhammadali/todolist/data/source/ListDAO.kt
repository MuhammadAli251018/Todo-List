package online.muhammadali.todolist.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDAO {

    @Query("SELECT * FROM TasksList")
    fun getAllLists(): Flow<List<TasksList>>

    @Query("SELECT * FROM TasksList WHERE listId LIKE :id")
    fun getListById(id: Long): Flow<List<TasksList>>

    @Delete
    suspend fun deleteList(): Result<Boolean>

    @Insert
    suspend fun addList(list: TasksList): Result<Long>

    @Update
    suspend fun updateList(list: TasksList): Result<Long>
}