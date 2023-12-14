package online.muhammadali.todolist.common.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
    innerPadding: PaddingValues = PaddingValues(15.dp),
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
        contentPadding = innerPadding
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
    innerPadding: PaddingValues = PaddingValues(15.dp),
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
        contentPadding = innerPadding,
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

data class TextFieldIcon(
    val painter: Painter,
    val description: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcon(
    modifier: Modifier = Modifier,
    label: String?,
    text: String,
    placeholderText: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    textFontSize: TextUnit,
    labelFontSize: TextUnit,
    labelTextColor: Color = Color.White.copy(alpha = 0.7f),
    leadingIcon: TextFieldIcon? = null,
    trailingIcon: TextFieldIcon? = null,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit ={}
) {

    Column (
        modifier = modifier
    ){

        if (label != null)
            Text(
                text = label,
                color = labelTextColor,
                fontSize = labelFontSize
            )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            textStyle = TextStyle(
                fontSize = textFontSize
            ),
            shape = RectangleShape,
            singleLine = true,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable(onClick = leadingIcon.onClick),
                        painter = leadingIcon.painter,
                        contentDescription = leadingIcon.description,
                        tint = Color.White
                    )
                }
            } else
                null,
            trailingIcon = if (trailingIcon != null){
                {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable(onClick = trailingIcon.onClick),
                        painter = trailingIcon.painter,
                        contentDescription = trailingIcon.description,
                        tint = Color.White
                    )
                }
            }
            else
                null,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = if (placeholderText != null){
                @Composable { Text(text = placeholderText) }
            } else null,
            onValueChange = onValueChange
        )
    }
}


@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    width: Dp,
    color: Color
) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            strokeWidth = width.toPx(),
            start = Offset.Zero,
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Composable
fun DividerWithText(
    modifier: Modifier = Modifier,
    width: Dp,
    color: Color,
    text: String,
    textSize: TextUnit,
    textColor: Color,
    textWeight: FontWeight,
    innerPadding: PaddingValues
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        HorizontalLine(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(innerPadding),
            width = width,
            color = color
        )
        
        HorizontalSpace(width = 10.dp)

        Text(
            text =text,
            fontSize = textSize,
            color = textColor,
            fontWeight = textWeight
        )

        HorizontalSpace(width = 10.dp)

        HorizontalLine(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(innerPadding),
            width = width,
            color = color
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    showPassword: Boolean,
    labelTextColor: Color = Color.White.copy(alpha = 0.7f),
    textFontSize: TextUnit,
    labelFontSize: TextUnit,
    onShowPasswordClicked: () -> Unit,
    onValueChange: (String) -> Unit
) {


    Column (
        modifier = modifier
    ){
        Text(
            text = label,
            color = labelTextColor,
            fontSize = labelFontSize
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            textStyle = TextStyle(
                fontSize = textFontSize
            ),
            visualTransformation = if (showPassword)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            shape = RectangleShape,
            singleLine = true,
            leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.lock1),
                        contentDescription = "Password",
                        tint = Color.White
                    )
                },
            trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = onShowPasswordClicked)
                            .size(25.dp),
                        painter = if (showPassword)
                            painterResource(id = R.drawable.eye)
                            else
                                painterResource(id = R.drawable.eyeslash),
                        contentDescription = "Password",
                        tint = Color.White
                    )
                },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = onValueChange
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
        /*PasswordTextField(
            label = "Enter Email",
            text = "blabla@gmail.com",
            textFontSize = 20.sp,
            labelFontSize = 18.sp
        ) {}*/

        DividerWithText(
            Modifier.fillMaxWidth(),
            width = 1.dp,
            color = Color.White,
            text = "",
            textSize = 20.sp,
            textColor = Color.White,
            textWeight = FontWeight.Bold,
            innerPadding = PaddingValues(20.dp)
        )

    }
}