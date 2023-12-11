package online.muhammadali.todolist.feature_auth.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import online.muhammadali.todolist.feature_auth.presentation.viewmodel.LogInViewModel
import online.muhammadali.todolist.feature_auth.presentation.viewmodel.SignUpViewModel

enum class AuthScreen (val rout: String) {
    OnBoarding("on_boarding")
}

@Composable
fun AuthNavHost(
   navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.OnBoarding.rout
    ) {
        composable(AuthScreen.OnBoarding.rout) {
            OnBoardingScreen(navHostController = navController) {/*Todo: Add a view model here*/}
        }
    }
}