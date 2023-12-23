package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import online.muhammadali.todolist.feature_main.presentation.viewmodel.controller.TaskContentSController

@Composable
fun ConfirmDeleteDialog(
    modifier: Modifier = Modifier,
    showState: Boolean,
    onDeleteTask: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showState){
        AlertDialog(
            modifier = modifier,
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = onDeleteTask,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Delete")
                }
            },
            shape = RectangleShape,
            containerColor = MaterialTheme.colorScheme.secondary,
            text = { Text(text = "Delete the Task?") },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Keep it")
                }
            }
        )
    }
}

@Composable
fun TaskContentScreen(
    stateController: TaskContentSController
) {
    val taskTitle by stateController.taskTitle.collectAsState(initial = "")
    val date by stateController.taskDate.collectAsState(initial = "")
    val taskDescription by stateController.taskDescription.collectAsState(initial = "")
    var showDatePicker by remember { mutableStateOf(false) }

    ConfirmDeleteDialog(
        showState = showDatePicker,
        onDeleteTask = stateController::onDeleteTask,
        onDismiss = {
            showDatePicker = false
        }
    )

    TaskContentScreen(
        taskTitle = taskTitle,
        taskDate = date,
        taskDescription = taskDescription,
        onEditTaskClick = stateController::onEditTaskClick,
        onDeleteTask = {
            showDatePicker = true
        }
    )
}
@Composable
fun TaskContentScreen(
    taskTitle: String,
    taskDate: String,
    taskDescription: String,
    onEditTaskClick: () -> Unit,
    onDeleteTask: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        
        VerticalSpace(height = 40.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 30.sp,
            color = DarkWhite,
            text = taskTitle,
            fontFamily = ArhayakibreFontFamily,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)


        Row(
            modifier = Modifier
                .padding(start = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(50.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar_ic),
                        contentDescription = "Date",
                        tint = Color.Black
                    )
                }
            }

            HorizontalSpace(width = 15.dp)

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Due date",
                    color = DarkWhite.copy(alpha = 0.5f),
                    fontSize = 10.sp
                )

                VerticalSpace(height = 5.dp)

                Text(
                    text = taskDate,
                    color = DarkWhite,
                    fontSize = 15.sp
                )
            }
        }

        VerticalSpace(height = 60.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Task Details",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 14.sp,
            color = DarkWhite,
            text = taskDescription
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .weight(1f)
                            .padding(vertical = 30.dp, horizontal = 10.dp),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red.copy(alpha = 0.45f)
                        ),
                        onClick = onDeleteTask
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Delete",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .weight(1f)
                            .padding(vertical = 30.dp, horizontal = 10.dp),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = onEditTaskClick
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Edit Task",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun TaskContentScreenPreview() {
    TodoListTheme{
        TaskContentScreen(
            taskTitle = "Play Toda",
            taskDate = "20 June",
            taskDescription = "don't worry don't worry don't worry don't worry don't worry don't worry don't worry ",
            {}
        ) {}
    }
}