package online.muhammadali.todolist.common.presentation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder

interface Screen {

    val route: String
    val arguments: List<NamedNavArgument>

    fun setupScreen (
        navGraphBuilder: NavGraphBuilder
    )

}