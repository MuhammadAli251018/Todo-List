package online.muhammadali.todolist.feature_main.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily

/** when passing value bigger that 1 it's considered as 1 */
@Composable
fun CircularProgressBar (
    modifier: Modifier,
    startAngle: Float = 270f,
    sweepAngle: Float,
    length: Dp,
    color: Color,
    thickness: Dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(length)
        ) {

            drawArc(
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                color = color,
                useCenter = false,
                topLeft = Offset.Zero,
                size = Size(length.toPx(), length.toPx()),
                style = Stroke(
                    thickness.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

class UnitsConverter(
    density: Density
) : Density {
    override val density: Float = density.density
    override val fontScale: Float = density.fontScale

    fun textUnitToDp(textUnit: TextUnit) = textUnit.toDp()
}


@Composable
fun CircularProgressBarWithPercentage(
    modifier: Modifier = Modifier,
    completeness: Float,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontColor: Color,
    progressColor: Color,
    thickness: Dp
) {

    val sweepAngle = (if (completeness > 1f) 1f else completeness) * 360f

    val converter = UnitsConverter(Density(LocalContext.current))


    Box (
        modifier,
        contentAlignment = Alignment.Center
    ){

        CircularProgressBar(
            modifier = Modifier,
            sweepAngle = sweepAngle,
            length = converter.textUnitToDp(fontSize * 4),
            color = progressColor,
            thickness = thickness
        )

        Text(
            text = "${(completeness * 100).toInt()}%",
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = fontColor,
            fontWeight = fontWeight
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CircularProgressBarPreview() {
    CircularProgressBarWithPercentage(
        modifier = Modifier,
        completeness = 0.75451f,
        progressColor = Color.Green,
        thickness = 2.dp,
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold,
        fontColor = Color.Black,
        fontFamily = ArhayakibreFontFamily
    )


}