package online.muhammadali.todolist.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    priorityTagColor: Color,
    priorityTagHeight: Dp,
    innerPaddingValues: PaddingValues
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(priorityTagHeight)
                .background(priorityTagColor)
        )

        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            //  Task Title
            Text(
                text = "Task title",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                placeholder = {
                    Text(text = "Finish home work, go fishing ...",
                        color= Color.Black.copy(alpha = 0.5f)
                    )
                },
                onValueChange = {},
                textStyle = TextStyle(
                    fontSize = 15.sp
                )
            )

            Spacer(modifier = Modifier.height(30.dp))


            Text(
                text = "Task description",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))


            //  Task Description
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                placeholder = {
                    Text(text = "Type More details here",
                        color= Color.Black.copy(alpha = 0.5f)
                    )
                },
                onValueChange = {},
                textStyle = TextStyle(
                    fontSize = 15.sp
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            //  todo implement priority selector

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen(
        priorityTagColor = Color.Red,
        priorityTagHeight = 4.dp,
        innerPaddingValues = PaddingValues(horizontal = 10.dp)
    )
}