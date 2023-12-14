package online.muhammadali.todolist.feature_main.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState


@Composable
fun HomeSubScreen(
    /*projects: List<ProjectState>,
    todayTasks: List<TaskState>*/
    userNameInit: String,
    userImagePainter: Painter,
    tasksList: List<TaskItemState>,
    onSearchKeywordChange: (newKeyword: String) -> Unit,
) {
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        VerticalSpace(height = 30.dp)

        Text(
            text = "Today Tasks"
        )
    }
}


@Preview
@Composable
fun HomeSubScreenPreview() {
    TodoListTheme {
        /*val projects = mutableListOf<ProjectState>()
        val tasks = mutableListOf<TaskState>()

        for (i in 0 .. 9) {
            projects.add(previewProjectState)
            tasks.add(previewTaskState.copy(modifier = previewTaskState.modifier.padding(horizontal = 5.dp)))
        }*/

        /*HomeSubScreen(
            /*projects = projects,
            todayTasks = tasks*/
        )*/
    }
}