package online.muhammadali.todolist.feature_main.presentation.screens.main

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitHorizontalDragOrCancellation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import kotlin.math.roundToInt


/*
* TODO:
*   1- make a selected day in the days list
*   2- make the first task with primary background
*   3- add drag right as done and left as delete
* */


data class DayState(
    val dayOfMonth: Int,
    val dayOfWeek: String
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
            containerColor = MaterialTheme.colorScheme.primary
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
                color = MaterialTheme.colorScheme.secondary
            )

            VerticalSpace(height = 10.dp)

            Text(
                text = dayState.dayOfWeek,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary
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

data class CalendarTaskState(
    val title: String,
    val time: String,
    val ownerImage: Painter
)

data class ItemBackgroundState(
    val iconId: Int,
    val iconDescription: String,
    val backgroundColor: Color
)

@Composable
fun CalendarTask(
    modifier: Modifier = Modifier,
    taskCalendarState: CalendarTaskState,
    onSwiped: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val primary = MaterialTheme.colorScheme.primary
    var cardColor by remember {
        mutableStateOf(primary)
    }
    Card(
        modifier = modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults
            .cardColors(
                containerColor = cardColor
            )
    ) {
        var horizontalDistance by remember { mutableFloatStateOf(0f) }

        val swipeToEndState = ItemBackgroundState(
            iconId = R.drawable.user,
            iconDescription = "Set task as Done",
            backgroundColor = Color.Green
        )
        val swipeToStartState = ItemBackgroundState(
            iconId = R.drawable.usertag,
            iconDescription = "Delete Task",
            backgroundColor = Color.Red
        )

        var backgroundState by remember { mutableStateOf(swipeToEndState) }
        
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            /*Icon(
                modifier = Modifier
                    .padding(start = 15.dp),
                painter = painterResource(id = backgroundIcon),
                contentDescription = ""
            )*/
            
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
                    .offset {
                        val change = horizontalDistance.roundToInt()
                        Log.d("UIDebug", "swipe change: $change")

                        IntOffset(change, 0)
                    }
                    .pointerInput(Unit) {
                        coroutineScope.launch {
                            detectHorizontalDragGestures(
                            ) { change, dragAmount ->
                                change.consume()
                                horizontalDistance += dragAmount

                                if (horizontalDistance > 0f) {
                                    cardColor = Color.Green
                                }

                                log = "x-distance: $horizontalDistance"
                            }
                        }
                    }
                    .height(70.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                val (title, time, owner) = createRefs()

                Text(
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(
                                parent.top,
                                margin = 10.dp
                            )

                            start.linkTo(parent.start, margin = 10.dp)
                        },
                    text = "Title",
                    color = DarkWhite,
                    fontSize = 20.sp
                )

                Text(
                    modifier = Modifier
                        .constrainAs(time) {
                            top.linkTo(title.bottom, margin = 10.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                        },
                    text = "8:30 PM",
                    color = DarkWhite
                )

                Image(
                    modifier = Modifier
                        .constrainAs(owner) {
                            top.linkTo(
                                parent.top,
                                margin = 10.dp
                            )

                            centerVerticallyTo(parent)

                            end.linkTo(parent.end, margin = 10.dp)
                        },
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google"
                )
            }
        }
    }
}

@Composable
fun CalendarTasksColumn(
    modifier: Modifier = Modifier,
    taskCalendarStates: List<CalendarTaskState>
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(taskCalendarStates) {index, state ->
            CalendarTask(
                taskCalendarState = state,
                onSwiped = {}
            )
        }
    }
}

@Composable
fun CalendarSubScreen(
    days: List<DayState>,
    tasks: List<CalendarTaskState>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {

        VerticalSpace(height = 50.dp)

        // TODO: Use alert dialog to choose the month and the year

        Text (
            text = "November",
            fontSize = 20.sp,
            color = DarkWhite,
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 30.dp)

        DaysRow(
            modifier = Modifier,
            dayStates = days,
            onClick = {}
        )

        VerticalSpace(height = 30.dp)

        Text(
            fontSize = 20.sp,
            color = DarkWhite,
            text = "Today's Tasks",
            fontWeight = FontWeight.Bold
        )

        VerticalSpace(height = 30.dp)

        CalendarTasksColumn(
            taskCalendarStates = tasks
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    TodoListTheme {


        val previewDayState = DayState(
            dayOfMonth = 1,
            dayOfWeek = "Tus"
        )

        val previewCalendarTask = CalendarTaskState(
            title = "Task",
            time = "8:20 PM",
            painterResource(id = R.drawable.google)
        )

        val days = mutableListOf<DayState>()
        val tasks = mutableListOf<CalendarTaskState>()

        for (i in 0 .. 7) {
            days.add(previewDayState)
            tasks.add(previewCalendarTask)
        }

        CalendarSubScreen(
            days = days,
            tasks = tasks
        )
    }
}