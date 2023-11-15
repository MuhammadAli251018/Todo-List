package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.feature_main.presentation.components.ParentTaskState
import online.muhammadali.todolist.feature_main.presentation.components.TaskState
import online.muhammadali.todolist.feature_main.presentation.components.TasksList

@Composable
fun MainScreen(priorityTagColor: Color,
               priorityTagHeight: Dp,
               innerPaddingValues: PaddingValues,
               taskTitle: String,
               titleFontSize: TextUnit,
               titleFontWeight: FontWeight,
               titleFontColor: Color,
               tasks: List<TaskState>,
               onTaskClicked: (Int) -> Unit,
               onTaskSelected: (Int) -> Unit,
               onTaskChecked: (Int, Boolean) -> Unit,
               onAddTask: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){

            Box (modifier = Modifier
                .fillMaxWidth()
                .height(priorityTagHeight)
                .background(priorityTagColor)
            )

            Column (
                modifier = Modifier
                    .padding(innerPaddingValues)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                //  Task Title
                Text(
                    text = taskTitle,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    color = titleFontColor
                )


                Spacer(modifier = Modifier.height(40.dp))


                Card(
                    modifier = Modifier,
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    TasksList(
                        tasks = tasks,
                        onTaskClicked,
                        onTaskSelected,
                        onTaskChecked
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 60.dp)
                .clickable(onClick = onAddTask)
                .size(70.dp),
            shape = CircleShape,
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add new task"
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val prototypeList = mutableListOf<ParentTaskState>()
    val parentTaskState = ParentTaskState(
        modifier = Modifier,
        taskTitle = "Test Task",
        taskCompleteness = 0.75f,
        backgroundColor = Color.White,
        textColor = Color.Black,
        progressColor1 = Color.Red,
        progressColor2 = Color.Green,
        height = 35.dp,
        priorityTagColor = Color.Red,
        priorityTagWidth = 8.dp,
        innerVerticalPadding = 20.dp
    )
    for (i in 0..10)
        prototypeList.add(parentTaskState)


    MainScreen(
        priorityTagColor = Color.Red,
        priorityTagHeight = 4.dp,
        innerPaddingValues = PaddingValues(start = 10.dp),
        taskTitle = "TASKS",
        titleFontSize = 25.sp,
        titleFontWeight = FontWeight.Normal,
        titleFontColor = Color.Black,
        tasks = prototypeList,
        onTaskClicked = {},
        onTaskSelected = {},
        onTaskChecked = { _, _ -> }
    ) {

    }
}