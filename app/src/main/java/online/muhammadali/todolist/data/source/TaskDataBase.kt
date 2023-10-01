package online.muhammadali.todolist.data.source

import androidx.room.Database

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase {
    abstract fun getDAO(): TasksDAO
}