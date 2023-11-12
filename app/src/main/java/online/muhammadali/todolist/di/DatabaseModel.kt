package online.muhammadali.todolist.di

import online.muhammadali.todolist.feature_main.data.repository.TaskDbRepo

class DatabaseModel(
    override val initializer: Initializer<TaskDbRepo>
) : DependencyModel<TaskDbRepo>(){

}