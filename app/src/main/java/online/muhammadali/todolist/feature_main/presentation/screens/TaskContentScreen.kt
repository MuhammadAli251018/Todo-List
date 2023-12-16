package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.HorizontalSpace
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import online.muhammadali.todolist.feature_main.presentation.components.TaskItemState

@Composable
fun TaskContentScreen(
    taskTitle: String,
    taskDate: String,
    taskDescription: String
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
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .padding(vertical = 30.dp)
                            .fillMaxWidth(0.8f),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
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
            taskDescription = "don't worry don't worry don't worry don't worry don't worry don't worry don't worry "
        )
    }
}