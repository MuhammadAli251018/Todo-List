package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.DarkWhite
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme

@Composable
fun MoreCircle(
    modifier: Modifier,
    radius: Dp
) {
    Canvas(
        modifier = modifier
    ) {
        drawCircle(
            color = Color.Gray,
            radius = radius.toPx()
        )

        val workingLine = (radius / 4).toPx()

        for ( i in -1 .. 1)
            drawCircle(
                center = center + (Offset(x = workingLine, y = 0f).times(i.toFloat())),
                color = Color.White.copy(alpha = 0.5f),
                radius = (radius / 10).toPx()
            )

    }
}

// TODO Complete
@Composable
fun SmallProfiles(
    modifier: Modifier,
    images: List<Painter>, radius: Dp
) {
    val count = if (images.size > 3 ) 3 else images.size
    val width = ((count - 1) * ((4f/3f) * radius)) + 2 * radius
    Box(
        modifier = modifier
            .size(height = 2 * radius, width = width)
            .padding(5.dp),
    ) {
        for (index in 0 .. count) {
            val image = images[index]

            val positionFormula: Density.() -> IntOffset = {
                val x = (index * 4/3 * radius.toPx()).toInt()
                IntOffset(x, 0)
            }
            if (index < 3)
                Image(
                    modifier = Modifier
                        //.background(Color.Gray)
                        .offset(positionFormula),
                    painter = image,
                    contentDescription = "team member"
                )
            else
                MoreCircle(
                    modifier = Modifier
                        .offset(positionFormula),
                    radius = radius
                )
        }
    }
}

@Composable
fun ProjectsColumn(
    projectStates: List<ProjectState>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(projectStates) {index, state ->
            ProjectItem(projectState = state) {
                onItemClick(index)
            }
        }
    }
}


data class ProjectState(
    val modifier: Modifier,
    val title: String,
    val fontSize: TextUnit,
    val fontFamily: FontFamily,
    val fontWeight: FontWeight,
    val fontColor: Color,
    val membersProfilePictures: List<Painter>,
    val date: String,
    val completeness: Float
)

val previewProjectState = ProjectState(
    modifier = Modifier.padding(vertical= 10.dp),
    title = "Project Title",
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = ArhayakibreFontFamily,
    fontColor = Color.White,
    membersProfilePictures = emptyList(),
    date = "Due on 21 March",
    completeness = 0.7f
)

@Composable
fun ProjectItem(
    projectState: ProjectState,
    onClick: () -> Unit
) {
    ProjectItem(
        modifier = projectState.modifier,
        title = projectState.title,
        fontSize = projectState.fontSize,
        fontFamily = projectState.fontFamily,
        fontWeight = projectState.fontWeight,
        fontColor = projectState.fontColor,
        membersProfilePictures = projectState.membersProfilePictures,
        date = projectState.date,
        completeness = projectState.completeness,
        onClick = onClick
    )
}

@Composable
fun ProjectItem(
    modifier: Modifier,
    title: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontColor: Color,
    membersProfilePictures: List<Painter>,
    date: String,
    completeness: Float,
    onClick: () -> Unit
) {
    Card (
        modifier = modifier,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.secondary
        ),
    ) {
        VerticalSpace(height = 10.dp)


        Box (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column {

                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    text = title,
                    maxLines = 1,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight,
                    color = fontColor
                )

                VerticalSpace(height = 5.dp)

                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    text = "Team members",
                    fontSize = 12.sp,
                    color = DarkWhite
                )

                VerticalSpace(height = 20.dp)


                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    text = date,
                    fontSize = 12.sp,
                    color = DarkWhite
                )
            }

            CircularProgressBarWithPercentage(
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .align(Alignment.BottomEnd),
                completeness = completeness,
                fontSize = 13.sp,
                fontFamily = ArhayakibreFontFamily,
                fontWeight = FontWeight.Bold,
                fontColor = Color.White,
                progressColor = MaterialTheme.colorScheme.primary,
                thickness = 1f.dp
            )
        }
    }
}


@Preview
@Composable
fun ProjectItemPreview() {
    /*SmallProfiles(
        modifier = Modifier,
        images = listOf(
            painterResource(id = R.drawable.google),
            painterResource(id = R.drawable.user),
            painterResource(id = R.drawable.usertag),
            painterResource(id = R.drawable.eye),
        ),
        radius = 10.dp
    )*/

    val list = mutableListOf<ProjectState>()

    for ( i  in 0 .. 10)
        list.add(previewProjectState)

    TodoListTheme {
        ProjectsColumn(projectStates = list, onItemClick = {})
    }
}