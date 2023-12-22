package online.muhammadali.todolist.feature_main.presentation.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState
import online.muhammadali.todolist.feature_main.presentation.components.TasksColumn
import online.muhammadali.todolist.feature_main.presentation.components.previewTaskState
import online.muhammadali.todolist.feature_main.presentation.viewmodel.controller.CalendarSController

data class DayState(
    val dayOfMonth: Int,
    val dayOfWeek: String,
    val selected: Boolean
)

var log: String = ""
    set(value) {
        Log.d("UIDebug", value)
    }

@Composable
fun Day(
    modifier: Modifier,
    dayState: DayState,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor =
            if (dayState.selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = dayState.dayOfMonth.toString(),
                fontSize = 20.sp,
                color = if (dayState.selected)
                    MaterialTheme.colorScheme.secondary
                else
                    DarkWhite
            )

            VerticalSpace(height = 10.dp)

            Text(
                text = dayState.dayOfWeek,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (dayState.selected)
                    MaterialTheme.colorScheme.secondary
                else
                    DarkWhite
            )

        }
    }
}

@Composable
fun DaysRow(
    modifier: Modifier,
    dayStates: List<DayState>,
    onClick: (Int) -> Unit
) {
    Box(modifier = modifier){
        LazyRow {
            itemsIndexed(dayStates) { index, state ->
                Day(
                    modifier = Modifier.padding(horizontal = 7.dp),
                    dayState = state
                ) {
                    onClick(index)
                }
            }
        }
    }
}

@Composable
fun CalendarSubScreen(
    stateController: CalendarSController
) {

    val days by stateController.days.collectAsStateWithLifecycle(initialValue = emptyList())
    val tasks by stateController.tasks.collectAsStateWithLifecycle(initialValue = emptyList())
    val history by stateController.history.collectAsStateWithLifecycle(initialValue = "")

    CalendarSubScreen(
        days = days,
        tasks = tasks,
        history = history,
        onDayClick = stateController::onDayClick,
        onHistoryClick = { /*TODO*/ },
        onTaskCompleted = stateController::onTaskCompleted,
        onTaskClicked = stateController::onTaskClicked
    )
}

// stateless Calendar composable
@Composable
fun CalendarSubScreen(
    days: List<DayState>,
    tasks: List<TaskItemState>,
    history: String,
    onDayClick: (Int) -> Unit,
    onHistoryClick: () -> Unit,
    onTaskCompleted: (Int) -> Unit,
    onTaskClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {

        VerticalSpace(height = 50.dp)

        Text (
            modifier = Modifier.clickable(onClick = onHistoryClick),
            text = history,
            fontSize = 20.sp,
            color = DarkWhite,
            fontWeight = FontWeight.Normal
        )

        VerticalSpace(height = 30.dp)

        DaysRow(
            modifier = Modifier,
            dayStates = days,
            onClick = onDayClick
        )

        VerticalSpace(height = 30.dp)

        Text(
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Today's Tasks",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 30.dp)

        TasksColumn(
            tasksState = tasks,
            onItemClick = onTaskClicked,
            onTaskCompleted = onTaskCompleted
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    TodoListTheme {

        val previewDayState = DayState(
            dayOfMonth = 1,
            dayOfWeek = "Tus",
            selected = false
        )

        val days = mutableListOf<DayState>()
        val tasks = mutableListOf<TaskItemState>()

        for (i in 0 .. 7) {

            days.add(if (i == 3)
                previewDayState.copy(selected = true)
            else previewDayState)

            tasks.add(previewTaskState)
        }

        CalendarSubScreen(
            days = days,
            tasks = tasks,
            "November",
            {},{}, {}, {}
        )
    }
}