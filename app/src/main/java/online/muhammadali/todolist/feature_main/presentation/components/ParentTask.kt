package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//  todo when task is selected all other actions
//   should be stopped until the selection is disabled

private const val SIZE_RATIO = 16f / 30f

data class RbgOperator(
    val red: Float,
    val blue: Float,
    val green: Float,
    val alpha: Float
) {
    fun toColor() = Color(
        red, green, blue, alpha
    )
}

fun Color.toRbgOperator() = RbgOperator(red, blue, green, alpha)

operator fun RbgOperator.minus(
    otherColor: RbgOperator
) = RbgOperator(
        red = red - otherColor.red,
        green = green - otherColor.green,
        blue = blue - otherColor.blue,
        alpha = alpha - otherColor.alpha
    )

operator fun RbgOperator.plus(
    otherColor: RbgOperator
) = RbgOperator(
    red = red + otherColor.red,
    green = green + otherColor.green,
    blue = blue + otherColor.blue,
    alpha = alpha + otherColor.alpha
)


operator fun RbgOperator.times(
    factor: Float
): RbgOperator = RbgOperator(
    red = red * factor,
    green = green * factor,
    blue = blue * factor,
    alpha = alpha
    )

sealed interface TaskState {
    val modifier: Modifier
    val taskTitle: String
    val backgroundColor: Color
    val textColor: Color
    val height: Dp
    val priorityTagColor: Color
    val priorityTagWidth: Dp
    val innerVerticalPadding: Dp
}

data class SubTaskState(
    override val modifier: Modifier,
    override val taskTitle: String,
    override val backgroundColor: Color,
    override val textColor: Color,
    val checkBoxColor: Color,
    override val priorityTagColor: Color,
    override val priorityTagWidth: Dp,
    override val height: Dp,
    val isCompleted: Boolean,
    override val innerVerticalPadding: Dp

) : TaskState

data class ParentTaskState(
    override val modifier: Modifier,
    override val taskTitle: String,
    override val backgroundColor: Color,
    override val textColor: Color,
    val progressColor1: Color,
    val progressColor2: Color,
    override val priorityTagColor: Color,
    override val priorityTagWidth: Dp,
    override val height: Dp,
    val taskCompleteness: Float,
    override val innerVerticalPadding: Dp,
    ) : TaskState



@Composable
fun TasksList(
    tasks: List<TaskState>,
    onTaskClicked: (Int) -> Unit = {},
    onTaskSelected: (Int) -> Unit = {},
    onSubtaskChecked: (Int, Boolean) -> Unit = {_,_ -> }
) {
    LazyColumn(contentPadding = PaddingValues(vertical = 10.dp)) {
        itemsIndexed(tasks) {index, state ->
            when (state) {
                is ParentTaskState -> ParentTask(
                    state = state,
                    onClick = { onTaskClicked(index)},
                    onSelect = {onTaskSelected(index)}
                )

                is SubTaskState -> SubTask(
                    state = state,
                    onClick = {onTaskClicked(index)},
                    onSelect = { onTaskSelected(index)},
                    onChecked = {   isChecked ->
                        onSubtaskChecked(index, isChecked)
                    }
                )
            }
        }
    }
}

@Composable
fun SubTask(
    state: SubTaskState,
    onClick: () -> Unit,
    onSelect: () -> Unit,
    onChecked: (Boolean) -> Unit
) {
    SubTask(
        modifier = state.modifier,
        checkBoxColor = state.checkBoxColor,
        taskTitle = state.taskTitle,
        isCompletedInit = state.isCompleted,
        backgroundColor = state.backgroundColor,
        textColor = state.textColor,
        height = state.height,
        priorityTagColor = state.priorityTagColor,
        priorityTagWidth = state.priorityTagWidth,
        innerVerticalPadding = state.innerVerticalPadding,
        onClick = onClick,
        onSelect = onSelect,
        onChecked = onChecked
    )
}


@Composable
fun SubTask(
    modifier: Modifier = Modifier,
    taskTitle: String,
    checkBoxColor: Color,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.tertiary,
    isCompletedInit: Boolean,
    height: Dp = 35.dp,
    priorityTagColor: Color = Color.Red,
    priorityTagWidth: Dp = 8.dp,
    innerVerticalPadding: Dp,
    onClick: () -> Unit,
    onSelect: () -> Unit,
    onChecked: (Boolean) -> Unit
) {
    var isCompleted by remember {  mutableStateOf(isCompletedInit)  }

    //  todo Add Background color change when selected

    BasicSubTask(
        modifier = modifier.fillMaxWidth(),
        title = taskTitle,
        isCompleted = isCompleted,
        onClick = onClick,
        backgroundColor = backgroundColor,
        textColor = textColor,
        height = height,
        fontSize = (height.value * SIZE_RATIO).sp,
        priorityTagColor = priorityTagColor,
        priorityTagWidth = priorityTagWidth,
        checkBoxColor = checkBoxColor,
        innerVerticalPadding = innerVerticalPadding,
        onSelect = onSelect,
        onChecked =
        {
            isCompleted = !it
            onChecked(isCompleted)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicSubTask(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String,
    isCompleted: Boolean,
    height: Dp = 25.dp,
    innerSpace: Dp = 15.dp,
    checkBoxColor: Color = Color.Green,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    priorityTagColor: Color = Color.Red,
    priorityTagWidth: Dp = 8.dp,
    fontSize: TextUnit = 16.sp,
    innerVerticalPadding: Dp,
    onClick: () -> Unit,
    onSelect: () -> Unit,
    onChecked: (Boolean) -> Unit
) {

    Card(
        modifier = modifier
            .height(height + innerVerticalPadding)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onSelect
            ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
    ) {
        Row (
            //modifier = Modifier.padding(vertical = innerVerticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = onChecked,
                    colors = CheckboxDefaults.colors(
                        checkedColor = checkBoxColor,
                    )
                )

                Spacer(modifier = Modifier.width(innerSpace))

                Text(
                    text = title,
                    color = textColor,
                    fontSize = fontSize
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            )
            {
                PriorityTag(
                    color = priorityTagColor,
                    width = priorityTagWidth
                )
            }
        }
    }
}

@Composable
fun ParentTask(
    state: ParentTaskState,
    onClick: () -> Unit,
    onSelect: () -> Unit
) {
    ParentTask(
        modifier = state.modifier,
        taskTitle = state.taskTitle,
        taskCompleteness = state.taskCompleteness,
        backgroundColor = state.backgroundColor,
        textColor = state.textColor,
        progressColor1 = state.progressColor1,
        progressColor2 = state.progressColor2,
        height = state.height,
        priorityTagColor = state.priorityTagColor,
        priorityTagWidth = state.priorityTagWidth,
        innerVerticalPadding = state.innerVerticalPadding,
        onClick = onClick,
        onSelect = onSelect
    )
}

@Composable
fun ParentTask(
    modifier: Modifier = Modifier,
    taskTitle: String,
    taskCompleteness: Float,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textColor: Color = MaterialTheme.colorScheme.tertiary,
    progressColor1: Color = Color.Red,
    progressColor2: Color = Color.Green,
    height: Dp = 35.dp,
    priorityTagColor: Color = Color.Red,
    priorityTagWidth: Dp = 8.dp,
    innerVerticalPadding: Dp,
    onClick: () -> Unit,
    onSelect: () -> Unit
) {

    val progressColorVector =
        ((progressColor2.toRbgOperator() - progressColor1.toRbgOperator())
                * taskCompleteness) + progressColor1.toRbgOperator()

    BasicParentTask(
        modifier = modifier.fillMaxWidth(),
        title = taskTitle,
        completeness = taskCompleteness,
        onClick = onClick,
        backgroundColor = backgroundColor,
        textColor = textColor,
        progressBarColor = progressColorVector.toColor(),
        height = height,
        fontSize = (height.value * SIZE_RATIO).sp,
        priorityTagColor = priorityTagColor,
        priorityTagWidth = priorityTagWidth,
        innerVerticalPadding = innerVerticalPadding,
        onSelect = onSelect
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicParentTask(
    modifier: Modifier = Modifier,
    title: String,
    completeness: Float,
    height: Dp = 25.dp,
    innerSpace: Dp = 15.dp,
    progressBarColor: Color = Color.Green,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    priorityTagColor: Color = Color.Red,
    priorityTagWidth: Dp = 8.dp,
    fontSize: TextUnit = 16.sp,
    innerVerticalPadding: Dp,
    onClick: () -> Unit,
    onSelect: () -> Unit
) {

    Card(
        modifier = modifier
            .height(height + innerVerticalPadding)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onSelect
            ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
    ) {
        Row (
            //modifier = Modifier.padding(vertical = innerVerticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressBar(
                    completeness = completeness,
                    length = height / 2,
                    color = progressBarColor,
                    thickness = height.div(10)
                )

                Spacer(modifier = Modifier.width(innerSpace))

                Text(
                    text = title,
                    color = textColor,
                    fontSize = fontSize
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            )
            {
                PriorityTag(
                    color = priorityTagColor,
                    width = priorityTagWidth
                )
            }
        }
    }
}



@Composable
fun PriorityTag(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    width: Dp = 40.dp
) {
    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .width(width)
    ) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = size
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    /*Task(
        taskTitle = "Test Task",
        taskCompleteness = 0.75f,
        onClick = {},
        onSelect = {}
    )*/

    /*Box(
        modifier = Modifier.background(Color.White)
    ) {
        TasksList(

        )
    }*/

    BasicSubTask(
        title = "Test Subtask",
        isCompleted = false,
        onClick = {},
        onSelect = { },
        innerVerticalPadding = 5.dp,
        onChecked = {}
    )

}