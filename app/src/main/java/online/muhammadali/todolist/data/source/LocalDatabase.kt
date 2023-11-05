package online.muhammadali.todolist.data.source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getTaskDAO(): TasksDAO
}