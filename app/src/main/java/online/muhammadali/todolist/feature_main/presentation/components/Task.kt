package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.TextFieldIcon
import online.muhammadali.todolist.common.presentation.components.TextFieldWithIcon
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme



@Composable
fun TasksColumn(
    projectStates: List<ProjectState>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(projectStates) {index, state ->
            ProjectItem(
                projectState = state.copy(
                    modifier = state.modifier.fillMaxWidth()
                )
            ) {
                onItemClick(index)
            }
        }
    }
}

enum class SortState{
    BY_DATE_OF_TASK,
    BY_DATE_OF_CREATE
}

enum class SortDirection{
    ASC,
    DESC
}

sealed class SearchKeyWord {
    abstract val keyword: String
    data object Empty : SearchKeyWord(){
        override val keyword = "None"
    }
    data class Keyword(override val keyword: String) : SearchKeyWord()
}

data class TasksFilterState(
    val showCompleted: Boolean,
    val showUncompleted: Boolean,
    val searchKeyword: SearchKeyWord
)

private val initialFilterState = TasksFilterState(
    showCompleted = true,
    showUncompleted = true,
    searchKeyword = SearchKeyWord.Empty
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksFilter(
    modifier: Modifier,
    filterState: TasksFilterState = initialFilterState,
    onFilterChange: (TasksFilterState) -> Unit
) {
    var expand by remember { mutableStateOf(false) }
    
    Card (
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            TextFieldWithIcon(
                label = null,
                text = "",
                backgroundColor = MaterialTheme.colorScheme.background,
                textFontSize = 18.sp,
                labelFontSize = 12.sp,
                keyboardOptions = KeyboardOptions.Default,
                placeholderText = "Search",
                leadingIcon = TextFieldIcon(
                    painterResource(id = R.drawable.search_ic),
                    description = "Search in tasks",
                    onClick = {}
                )
            )

            VerticalSpace(height = 10.dp)

            if (expand)
                ExpandedFilter(
                    Modifier
                        .padding(vertical = 10.dp),
                    filterState,
                    onFilterChange = onFilterChange
                )
            else
                UnexpandedFilter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable {
                            expand = !expand
                        }
                )
        }
    }
}



@Composable
private fun ExpandedFilter (
    modifier: Modifier,
    filterState: TasksFilterState,
    onFilterChange: (TasksFilterState) -> Unit
) {

    ConstraintLayout (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (
            showCompletedCheckRef,
            showUnCompletedCheckRef,
            sortByRefRadioRef,
            orderByRadioRef
        ) = createRefs()

        Row (
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .constrainAs(showCompletedCheckRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                checked = filterState.showCompleted,
                onCheckedChange = {
                    onFilterChange(filterState.copy(showCompleted = it))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = DarkWhite,
                    uncheckedColor = DarkWhite
                ),
            )

            Text(
                text = "Show completed tasks",
                color = DarkWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .constrainAs(showUnCompletedCheckRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                checked = filterState.showUncompleted,
                onCheckedChange = {
                    onFilterChange(filterState.copy(showUncompleted = it))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = DarkWhite,
                    uncheckedColor = DarkWhite
                )
            )

            Text(
                text = "Show uncompleted tasks",
                color = DarkWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}

/*
@Composable
fun RowRadioGroup(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Show completed tasks",
            color = DarkWhite,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )

        RadioButton(
            selected = ,
            onClick = { /*TODO*/ }
        )
    }
}

 */

@Composable
private fun UnexpandedFilter(
    modifier: Modifier
) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Icon(
            // TODO: Use filter icon
            painter = painterResource(id = R.drawable.filter_ic),
            contentDescription = "filter results",
            tint = DarkWhite
        )
    }
}

data class TaskItemState(
    val title: String,
    val fontSize: TextUnit,
    val fontFamily: FontFamily,
    val fontWeight: FontWeight,
    val completed: Boolean
)

data class TaskItemDecorationState(
    val fontColor: Color,
    val backgroundColor: Color,
    val circleColor: Color,
    val textStyle: TextStyle
)


private val completedState
    @Composable get() =
    TaskItemDecorationState(
        fontColor = Color.White,
        backgroundColor = MaterialTheme.colorScheme.background,
        circleColor = MaterialTheme.colorScheme.primary,
        textStyle = TextStyle(textDecoration = TextDecoration.LineThrough)
    )

private val uncompletedState
    @Composable get() =
        TaskItemDecorationState(
            fontColor = DarkWhite,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            circleColor = MaterialTheme.colorScheme.background,
            textStyle = TextStyle(textDecoration = TextDecoration.None)
        )


val previewTaskState = TaskItemState(
    title = "Task Title",
    completed = false,
    fontFamily = ArhayakibreFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun TaskItem(
    modifier: Modifier,
    taskState: TaskItemState,
    onClick: () -> Unit,
    //onLongClick: () -> Unit
) {

    val decorationState = if (taskState.completed) completedState else uncompletedState

    TaskItem(
        modifier = modifier,
        title = taskState.title,
        fontSize = taskState.fontSize,
        fontFamily = taskState.fontFamily,
        fontWeight = taskState.fontWeight,
        fontColor = decorationState.fontColor,
        backgroundColor = decorationState.backgroundColor,
        textStyle = decorationState.textStyle,
        circleColor = decorationState.circleColor,
        onClick = onClick,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    modifier: Modifier,
    title: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontColor: Color,
    circleColor: Color,
    backgroundColor: Color,
    textStyle: TextStyle,
    //Todo: add -> date: String,
    //completeness: Float,
    onClick: () -> Unit,
) {
    //  todo complete
    Card (
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
            ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = backgroundColor
        )
    ) {
        Box (
            contentAlignment = Alignment.Center
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                val (titleRef, circle) = createRefs()

                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 15.dp)
                        .constrainAs(titleRef) {
                            start.linkTo(parent.start)
                        },
                    text = title,
                    maxLines = 1,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight,
                    color = fontColor,
                    style = textStyle
                )

                Card(
                    modifier = Modifier
                        .size(20.dp)
                        .constrainAs(circle) {
                            end.linkTo(parent.end)
                            centerVerticallyTo(parent)
                        }
                        .clickable(onClick = onClick),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = circleColor
                    )
                ) {}
            }
        }
    }
}


@Preview
@Composable
fun TaskItemPreview() {
    val list = mutableListOf<TaskItemState>()

    for ( i  in 0 .. 10)
        list.add(previewTaskState)

    TodoListTheme {
        /*TaskItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            taskState = previewTaskState, onClick = {}) {

        }*/

        TasksFilter(modifier =Modifier) {}
    }
}