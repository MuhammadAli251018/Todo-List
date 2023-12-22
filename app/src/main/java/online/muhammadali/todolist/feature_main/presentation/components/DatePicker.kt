package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    modifier: Modifier,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        colors = DatePickerDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        confirmButton = {
            Button(
                onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            },
            shape = RectangleShape) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            },
            shape = RectangleShape
            ) {
                Text(text = "Cancel")
            }
        },
        shape = RectangleShape
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}


@Preview
@Composable
fun DatePickerPreview() {
    TodoListTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatePickerDialog(
                modifier = Modifier.padding(horizontal = 10.dp),
                onDateSelected = {}) {

            }
        }
    }
}


