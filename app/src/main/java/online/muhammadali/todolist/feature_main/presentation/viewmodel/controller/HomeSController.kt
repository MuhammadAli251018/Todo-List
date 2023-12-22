package online.muhammadali.todolist.feature_main.presentation.viewmodel.controller

import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.flow.Flow
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState
import online.muhammadali.todolist.feature_main.presentation.components.TasksFilterState

interface HomeSController {
    val userName: String
    val userImagePainter: Painter
    val tasksList: Flow<List<TaskItemState>>
    val filterState: Flow<TasksFilterState>

    fun onUserProfileClick()
    fun onFilterStateChange(newState: TasksFilterState)
    fun onTaskItemClick(index: Int)
    fun onCompleteTask(index: Int)
}