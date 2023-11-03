package online.muhammadali.todolist.data.source

import androidx.room.Database

@Database(entities = [TasksList::class], version = 1)
abstract class ListDatabase {
    abstract fun getDAO(): ListDAO
}