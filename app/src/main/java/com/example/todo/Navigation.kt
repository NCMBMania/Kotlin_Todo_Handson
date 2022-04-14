package com.example.todo

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nifcloud.mbaas.core.NCMBObject

@Composable
fun Navigation() {
    val startDestination = "list"
    val navController = rememberNavController()
    val className = "Task"
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "form") {
            val obj = NCMBObject(className)
            FormScreen(navController, obj)
        }
        composable(route = "list") {
            ListScreen(navController = navController)
        }
        composable(
            route = "detail/task={task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType})
        ) { backStackEntry ->
            val obj = strToNCMBObject(backStackEntry.arguments!!.getString("task")!!, className)
            DetailScreen(navController, obj)
        }
        composable(
            route = "edit/task={task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType})
        ) { backStackEntry ->
            val obj = strToNCMBObject(backStackEntry.arguments!!.getString("task")!!, className)
            FormScreen(navController, obj)
        }
    }
}