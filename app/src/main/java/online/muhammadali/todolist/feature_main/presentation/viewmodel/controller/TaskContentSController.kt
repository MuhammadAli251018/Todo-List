package online.muhammadali.todolist.feature_main.presentation.viewmodel.controller

import kotlinx.coroutines.flow.Flow

interface TaskContentSController {

    val taskTitle: Flow<String>
    val taskDate: Flow<String>
    val taskDescription: Flow<String>

    fun onTitleChange(title: String)
    fun onDescriptionChange(description: String)
    fun onHistoryChange(newHistory: String)
    fun onSaveTask()
    fun onEditTaskClick()
    fun onDeleteTask()
}