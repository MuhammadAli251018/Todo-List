package online.muhammadali.todolist.feature_main.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import online.muhammadali.todolist.common.presentation.Screen

@Composable
fun MainActivityNav(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "main"
    ) {
        navigation(
            startDestination = "home",
            route = "main"
        ) {

        }

        navigation(
            startDestination = "home",
            route = "main"
        ) {

        }


        composable(
            route = "create_project",
            arguments = listOf( navArgument("" ){

                build()
            })
        ) {

        }

        composable(
            route = "profile"
        ) {

        }
    }
}