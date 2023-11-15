package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

//todo add a edit button to edit the task

@Composable
fun TaskContentScreen(
    priorityTagColor: Color,
    priorityTagHeight: Dp,
    innerPaddingValues: PaddingValues,
    taskTitle: String,
    titleFontSize: TextUnit,
    titleFontWeight: FontWeight,
    titleFontColor: Color,
    taskDescription: String,
    desFontSize: TextUnit,
    desFontWeight: FontWeight,
    desFontColor: Color,
    subtasksState: List<TaskState>,
    onSubtaskClicked: (Int) -> Unit,
    onSubtaskSelected: (Int) -> Unit,
    onSubtaskChecked: (Int, Boolean) -> Unit,
    onAddSubtask: () -> Unit
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

            Spacer(modifier = Modifier.height(20.dp))


            //  Task Description
            Text(
                text = taskDescription,
                fontSize = desFontSize,
                fontWeight = desFontWeight,
                color = desFontColor
            )

            Spacer(modifier = Modifier.height(40.dp))


            //  Subtask list title
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "subtasks",
                    fontSize = 24.sp,
                    color = Color.Black,
                    //fontWeight = FontWeight.Bold
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    IconButton(onClick = onAddSubtask) {
                        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "add subtask")
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))


            TasksList(
                tasks = subtasksState,
                onSubtaskClicked,
                onSubtaskSelected,
                onSubtaskChecked
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskContentScreenPreview() {
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

    TaskContentScreen(
        priorityTagColor = Color.Red,
        priorityTagHeight = 4.dp,
        innerPaddingValues = PaddingValues(start = 10.dp),
        taskTitle = "Task title",
        titleFontSize = 40.sp,
        titleFontWeight = FontWeight.Bold,
        titleFontColor = Color.Black,
        taskDescription = "bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla ",
        desFontSize = 18.sp,
        desFontWeight = FontWeight.Normal,
        desFontColor = Color.Black.copy(alpha = 0.5f),
        subtasksState = prototypeList,
        onSubtaskClicked = {},
        onSubtaskSelected = {},
        onSubtaskChecked = {_,_ -> }
    ) {
        
    }
}