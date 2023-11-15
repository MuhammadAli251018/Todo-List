package online.muhammadali.todolist.feature_auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import online.muhammadali.todolist.R
import online.muhammadali.todolist.common.presentation.components.DividerWithText
import online.muhammadali.todolist.common.presentation.components.FilledTextButton
import online.muhammadali.todolist.common.presentation.components.HorizontalSpace
import online.muhammadali.todolist.common.presentation.components.PasswordTextField
import online.muhammadali.todolist.common.presentation.components.StrokeButtonWithIcon
import online.muhammadali.todolist.common.presentation.components.TextFieldIcon
import online.muhammadali.todolist.common.presentation.components.TextFieldWithIcon
import online.muhammadali.todolist.common.presentation.components.VerticalSpace
import online.muhammadali.todolist.common.presentation.theme.TodoListTheme
import online.muhammadali.todolist.feature_auth.presentation.viewmodel.LogInViewModel

@Composable
fun LoginScreen(
    viewModel: LogInViewModel,
    navHostController: NavHostController
) {

    val email by viewModel.emailState.collectAsStateWithLifecycle()
    val password by viewModel.passwordState.collectAsStateWithLifecycle()
    val showPassword by viewModel.showPasswordState.collectAsStateWithLifecycle()
    val passwordLabel by viewModel.passwordLabelState.collectAsStateWithLifecycle()
    val emailLabel by viewModel.emailLabelState.collectAsStateWithLifecycle()
    val emailLabelColor by viewModel.emailLabelColorState.collectAsStateWithLifecycle()
    val passwordLabelColor by viewModel.passwordLabelColorState.collectAsStateWithLifecycle()

    LoginScreen(
        email = email,
        password = password,
        showPassword = showPassword,
        emailLabel = emailLabel,
        passwordLabel = passwordLabel,
        emailLabelColor = emailLabelColor,
        passwordLabelColor = passwordLabelColor,
        navHostController = navHostController,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onForgotPasswordClicked = viewModel::onForgotPasswordClicked,
        onLogInButtonClicked = viewModel::onLogInButtonClicked,
        onSignWithGoogleClicked = viewModel::onSignWithGoogleClicked,
        onSignUpClicked = viewModel::onSignUpClicked,
        onShowPasswordClicked = viewModel::onShowPasswordClicked
    )
}

@Composable
fun LoginScreen(
    //  states that depends on data from view model
    email: String,
    password: String,

    //  states that depends on ui state
    showPassword: Boolean,
    emailLabel: String,
    passwordLabel: String,
    emailLabelColor: Color,
    passwordLabelColor: Color,
    navHostController: NavHostController,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onLogInButtonClicked: () -> Unit,
    onSignWithGoogleClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onShowPasswordClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        VerticalSpace(height = 50.dp)


        Image(
            painter = BitmapPainter(
                ImageBitmap.Companion.imageResource(R.drawable.ic_done)
            ),/*painterResource(id = R.drawable.ic_done),*/
            contentDescription = "Done Logo"
        )

        VerticalSpace(height = 40.dp)

        Text(
            modifier = Modifier
                .padding(start = 30.dp)
                .align(Alignment.Start),
            text = "Welcome Back!",
            color = Color.White,
            fontSize = 30.sp

        )

        VerticalSpace(height = 20.dp)

        TextFieldWithIcon(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            label = emailLabel/*"Email"*/,
            labelTextColor = emailLabelColor,
            text = email,
            textFontSize = 18.sp,
            labelFontSize = 16.sp,
            leadingIcon = TextFieldIcon(
                painter = painterResource(id = R.drawable.usertag),
                description = "Email",
                onClick = {}
            ),
            keyboardOptions = KeyboardOptions
                (keyboardType = KeyboardType.Email
            ),
            onValueChange = onEmailChange
        )


        VerticalSpace(height = 20.dp)

        PasswordTextField(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            label = passwordLabel/*"Password"*/,
            labelTextColor = passwordLabelColor,
            text = password,
            textFontSize = 18.sp,
            labelFontSize = 16.sp,
            showPassword = showPassword,
            onShowPasswordClicked = onShowPasswordClicked,
            onValueChange = onPasswordChange
        )

        VerticalSpace(height = 20.dp)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.End)
                .clickable(onClick = onForgotPasswordClicked),
            text = "Forgot Password?",
            color = Color.White
        )

        VerticalSpace(height = 20.dp)

        FilledTextButton(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            text = "Create Account",
            textSize = 18.sp,
            onClick = onLogInButtonClicked
        )

        VerticalSpace(height = 30.dp)

        DividerWithText(
            modifier = Modifier.fillMaxWidth(),
            width = 1.dp,
            color = Color.White.copy(alpha =  0.7f ),
            text = "Or continue with",
            textSize = 14.sp,
            textColor = Color.White,
            textWeight = FontWeight.Normal,
            innerPadding = PaddingValues(horizontal = 30.dp)
        )

        VerticalSpace(height = 30.dp)

        StrokeButtonWithIcon(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            text = "Google",
            textSize = 18.sp,
            iconDescription = "Google",
            icon = painterResource(id = R.drawable.google),
            iconSize = 25.dp,
            iconColor = Color.White,
            onClick = onSignWithGoogleClicked
        )

        VerticalSpace(height = 20.dp)

        Row {
            Text(
                text = "Don't have account",
                color = Color.White
            )

            HorizontalSpace(width = 5.dp)

            Text(
                modifier = Modifier
                    .clickable (
                        onClick = {
                            onSignUpClicked()
                            navHostController.navigate(AuthScreen.SignUp.rout)
                        }
                    ),
                text = "SignUp",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TodoListTheme {
        //LoginScreen(viewObject)
    }
}