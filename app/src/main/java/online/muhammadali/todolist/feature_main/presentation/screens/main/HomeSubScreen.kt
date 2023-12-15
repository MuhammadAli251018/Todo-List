package online.muhammadali.todolist.feature_main.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.HorizontalSpace
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState
import online.muhammadali.todolist.feature_main.presentation.components.TasksColumn
import online.muhammadali.todolist.feature_main.presentation.components.TasksFilter
import online.muhammadali.todolist.feature_main.presentation.components.TasksFilterState
import online.muhammadali.todolist.feature_main.presentation.components.initialFilterState

// stateless screen composable
@Composable
fun HomeSubScreen(
    userNameImmutable: String,
    userImagePainter: Painter,
    tasksList: List<TaskItemState>,
    filterState: TasksFilterState,
    onUserProfileClick: () -> Unit,
    onFilterStateChange: (newState: TasksFilterState) -> Unit,
    onTaskItemClick: (index: Int) -> Unit,
    onCompletedTask: (index: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        VerticalSpace(height = 20.dp)

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalSpace(width = 10.dp)

            Text(
                text = userNameImmutable,
                fontFamily = ArhayakibreFontFamily,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DarkWhite
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(onClick = onUserProfileClick),
                    painter = userImagePainter,
                    contentDescription = "User Profile"
                )
            }
        }

        VerticalSpace(height = 10.dp)


        TasksFilter(
            modifier = Modifier
                .padding(horizontal = 5.dp),
            onFilterChange = onFilterStateChange,
            filterState = filterState
        )

        VerticalSpace(height = 20.dp)


        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "Tasks",
            fontFamily = ArhayakibreFontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkWhite
        )

        VerticalSpace(height = 20.dp)


        TasksColumn(
            tasksState = tasksList,
            onItemClick = onTaskItemClick,
            onTaskCompleted = onCompletedTask
        )
    }
}


@Preview
@Composable
fun HomeSubScreenPreview() {
    TodoListTheme {
        val previewTaskState = listOf(
            TaskItemState(
                title = "Play TODA",
                fontFamily = ArhayakibreFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                completed = false
            ),
            TaskItemState(
                title = "Play TODA",
                fontFamily = ArhayakibreFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                completed = false
            ),
            TaskItemState(
                title = "Play TODA",
                fontFamily = ArhayakibreFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                completed = false
            )
        )
        HomeSubScreen(
            userNameImmutable = "Muhammad Ali",
            userImagePainter = painterResource(id = R.drawable.usertag),
            tasksList = previewTaskState,
            filterState = initialFilterState,
            onCompletedTask = {},
            onFilterStateChange = {},
            onTaskItemClick = {},
            onUserProfileClick = {}
        )
    }
}