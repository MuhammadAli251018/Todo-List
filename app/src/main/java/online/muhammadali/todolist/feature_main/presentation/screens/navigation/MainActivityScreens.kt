package online.muhammadali.todolist.feature_main.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import online.muhammadali.todolist.common.presentation.Screen

fun NavGraphBuilder.setupScreen(
    screen: Screen,
    screenSetup: @Composable () -> Unit
) {
    composable(
        route = screen.route,
        arguments = screen.arguments
    ) {
        screenSetup()
    }
}

fun NavGraphBuilder.setupParentScreen(
    route: String,
    nestedScreens: List<Pair<Screen, @Composable () -> Unit>>
) {
    navigation(
        route = route,
        startDestination = nestedScreens[0].first.route
    ) {
        nestedScreens.forEach {
            setupScreen(it.first, it.second)
        }
    }
}

private fun getTodayDate() = "today-date"


sealed class MainActivityScreens(
    override val route: String,
    override val arguments: List<NamedNavArgument> = emptyList(),
) : Screen {


    data object HomeSubScreen : MainActivityScreens(
        route = "home_sub_screen"
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.setupScreen(this) {
                //  TODO
            }
        }
    }

    data object CalendarSubScreen : MainActivityScreens(
        route = "calendar_sub_screen",
        arguments = listOf(
            navArgument(
                name = "date"
            ) {
                type = NavType.StringType
                defaultValue = getTodayDate()
            }
        )
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.setupScreen(this) {
                //  TODO
            }
        }
    }

    data object MainScreen : MainActivityScreens(
        route = "main_screen",
        emptyList()
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.setupParentScreen(
                route = route,
                nestedScreens = listOf (
                    //Todo: Add screens here
                )
            )
        }
    }

    data object EditTaskScreen : MainActivityScreens(
        route = "create_project_screen"
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.setupScreen(this) {
                //  TODO
            }
        }
    }

    data object TaskContentScreen : MainActivityScreens(
        route = "project_content_screen",
        emptyList()
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            TODO("Not yet implemented")
        }
    }

    data object ProfileScreen : MainActivityScreens(
        route = "profile_screen"
    ) {
        override fun setupScreen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.setupScreen(this) {
                //  TODO
            }
        }
    }
}
