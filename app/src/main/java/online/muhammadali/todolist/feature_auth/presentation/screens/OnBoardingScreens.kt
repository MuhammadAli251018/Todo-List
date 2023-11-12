package online.muhammadali.todolist.feature_auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.FilledTextButton
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.ArhayakibreFontFamily
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme

@Composable
fun OnBoardingScreen(
    a: String
    //  todo add the navigation controller & the viewmodel
) {

}

//  stateless
@Composable
fun OnBoardingScreen(
    onGetStartedClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(height = 20.dp)

        Image(
            painter = painterResource(id = R.drawable.ic_done),
            contentDescription = "Done Logo"
        )


        VerticalSpace(height = 30.dp)

        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White)
        ){

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.pana),
                contentDescription = "Lets Get Started",
                contentScale = ContentScale.Crop
            )
        }

        VerticalSpace(height = 20.dp)


        val textList = arrayOf("Manage", "your", "Tasks With", "Done")

        textList.forEachIndexed { index, text ->
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 10.dp),
                text = text,
                fontFamily = ArhayakibreFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 45.sp,
                color =
                if (index == textList.lastIndex)
                    MaterialTheme.colorScheme.primary
                else
                    Color.White
            )

        }

        VerticalSpace(height = 20.dp)

        FilledTextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Let's GetStarted",
            textSize = 16.sp,
            onClick = onGetStartedClick
        )
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview(
    //  todo add the navigation controller & the viewmodel
) {
    TodoListTheme {
        OnBoardingScreen{

        }
    }
}