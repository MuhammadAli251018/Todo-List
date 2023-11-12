package online.muhammadali.todolist.common.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme

@Composable
fun HorizontalSpace(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun VerticalSpace(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun FilledTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape= RectangleShape,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        contentPadding = PaddingValues(20.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = textSize
        )
    }
}

@Composable
fun StrokeButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit,
    iconDescription: String,
    icon: Painter,
    iconSize: Dp,
    iconColor: Color,
    onClick: () -> Unit
    ) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape= RectangleShape,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
        contentPadding = PaddingValues(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.White)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Icon(
                modifier = Modifier.size(iconSize),
                painter = icon,
                contentDescription = iconDescription,
                tint = iconColor
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = text,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = textSize,
                textAlign = TextAlign.End
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    textFontSize: TextUnit,
    labelFontSize: TextUnit,
    leadingIcon: Painter?,
    leadingIconDes: String,
    trailingIconDes: String,
    trailingIcon: Painter?,
    keyboardOptions: KeyboardOptions,
) {

    Column (

        modifier = modifier
    ){


        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = labelFontSize
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = TextFieldValue(
                text
            ),
            textStyle = TextStyle(
                fontSize = textFontSize
            ),
            shape = RectangleShape,
            singleLine = true,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        modifier = Modifier
                            .size(45.dp),
                        painter = leadingIcon,
                        contentDescription = leadingIconDes,
                        tint = Color.White
                    )
                }
            } else
                null,
            trailingIcon = if (trailingIcon != null){
                {
                    Icon(
                        modifier = Modifier
                            .size(45.dp),
                        painter = trailingIcon,
                        contentDescription = trailingIconDes,
                        tint = Color.White
                    )
                }
            }
            else
                null,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            onValueChange = {

            }
        )
    }
}


@Preview
@Composable
fun ComponentsPreview() {
    TodoListTheme {
        /*StrokeButtonWithIcon (
            modifier = Modifier
                .fillMaxWidth(),
            text = "Android",
            textSize = 18.sp,
            //  todo remove the shadow
            icon = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            iconDescription = "icon",
            iconColor = Color.White,
            iconSize = 60.dp
        ){

        }*/
        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ){
            TextFieldWithIcon(
                label = "Enter Email",
                text = "blabla@gmail.com",
                textFontSize = 20.sp,
                labelFontSize = 18.sp,
                leadingIcon = painterResource(id = R.drawable.ic_launcher_foreground),
                leadingIconDes = "leading Icon",
                trailingIconDes = "trailing Icon",
                trailingIcon = painterResource(id = R.drawable.ic_launcher_foreground),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
    }
}