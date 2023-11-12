package online.muhammadali.todolist.feature_main.data.source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0L,
    val title: String,
    val description: String,
    val completeness: Float,
    val priority: Int,
    //todo add the default task
    val parentId: Long = -1L
)
