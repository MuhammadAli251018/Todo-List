package online.muhammadali.todolist.data.source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TasksList(
    @PrimaryKey(autoGenerate = true)
    val listId: Long = 0,
    val listName: String
)
