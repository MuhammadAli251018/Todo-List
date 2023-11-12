package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

/** when passing value bigger that 1 it's considered as 1 */
@Composable
fun CircularProgressBar (
    modifier: Modifier = Modifier,
    startAngle: Float = 270f,
    sweepAngle: Float,
    length: Dp,
    color: Color,
    thickness: Dp
) {
    Canvas(
        modifier = modifier
            .size(length)
    ) {

        drawArc(
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            color = color,
            useCenter = false,
            topLeft = Offset.Zero,
            size = size,
            style = Stroke(thickness.toPx(), cap = StrokeCap.Round)
        )
    }
}


@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    completeness: Float,
    length: Dp,
    color: Color,
    thickness: Dp
) {
    val sweepAngle = (if (completeness > 1f) 1f else completeness) * 360f

    CircularProgressBar(
        modifier = modifier,
        sweepAngle = sweepAngle,
        length = length,
        color = color,
        thickness = thickness
    )
}

@Preview(showBackground = true)
@Composable
fun CircularProgressBarPreview() {
    Box (
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        CircularProgressBar(
            completeness = 1f,
            length = 70.dp,
            color = Color.Green, thickness = 5.dp
        )
    }
}