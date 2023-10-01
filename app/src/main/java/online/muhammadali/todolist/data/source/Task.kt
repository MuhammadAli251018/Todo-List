package online.muhammadali.todolist.data.source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,
    val listId: Long,
    val taskContent: String,
    val completed: Boolean,
    val executionDate: String
)
