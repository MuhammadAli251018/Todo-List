package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.HorizontalSpace
import online.muhammadali.todolist.common.presentation.components.TextFieldWithIcon
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    date: String,
    taskTitle: String,
    taskDetails: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDateClick: () -> Unit,
    onCreateButtonClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        VerticalSpace(height = 40.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 30.sp,
            color = DarkWhite,
            text = "Create new Task",
            fontFamily = ArhayakibreFontFamily,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Date",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)


        Row(
            modifier = Modifier
                .padding(start = 30.dp)
                .background(color = MaterialTheme.colorScheme.secondary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(50.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = onDateClick
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

            HorizontalSpace(width = 10.dp)

            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date,
                    color = DarkWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        VerticalSpace(height = 20.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Task title",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)

        TextFieldWithIcon(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            label = null,
            text = taskTitle,
            textFontSize = 15.sp,
            labelFontSize = 15.sp,
            keyboardOptions = KeyboardOptions.Default,
            placeholderText = "Write the title here",
            onValueChange = onTitleChange
        )

        VerticalSpace(height = 20.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Task title",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 20.dp)

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(200.dp),
            value = taskDetails,
            textStyle = TextStyle(
                fontSize = 12.sp
            ),
            shape = RectangleShape,
            singleLine = false,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder =
                @Composable { Text(text = "placeholderText") },
            onValueChange = onDescriptionChange
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
                        ),
                        onClick = onCreateButtonClick
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
fun AddTaskScreenPreview() {
    TodoListTheme {
        AddTaskScreen(
            date = "20/7/2024",
            taskTitle = "Play Dota",
            taskDetails = "Don't worry Don't worry Don't worry Don't worry Don't worry Don't worry Don't worry ",
            {},
            {},
            {},
            {}
        )
    }
}