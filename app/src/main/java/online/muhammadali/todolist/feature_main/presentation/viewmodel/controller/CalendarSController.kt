package online.muhammadali.todolist.feature_main.presentation.viewmodel.controller

import kotlinx.coroutines.flow.Flow
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState
import online.muhammadali.todolist.feature_main.presentation.screens.main.DayState

interface CalendarSController {

    val days: Flow<List<DayState>>
    val tasks: Flow<List<TaskItemState>>
    val history: Flow<String>

    fun onDayClick(dayIndex: Int)
    fun onHistoryChange(history: String)
    fun onTaskCompleted(index: Int)
    fun onTaskClicked(index: Int)
}