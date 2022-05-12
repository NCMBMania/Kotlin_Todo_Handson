package com.example.todo

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// 1. NCMBObjectをインポートします

@Composable
fun Navigation() {
    val startDestination = "list"
    val navController = rememberNavController()
    // NCMBObject/NCMBQueryで扱うクラス名
    val className = "Task"
    NavHost(navController = navController, startDestination = startDestination) {
        // 入力画面
        composable(route = "form") {
            // 2. 新しいNCMBObjectを作成
            var obj = 0;
            FormScreen(navController, obj)
        }
        // 一覧画面
        composable(route = "list") {
            ListScreen(navController = navController)
        }
        // 詳細画面
        composable(
            route = "detail/task={task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType})
        ) { backStackEntry ->
            // 3. 受け取った文字列からNCMBObjectを復元します
            val obj = 0;
            DetailScreen(navController, obj)
        }
        // 編集画面
        composable(
            route = "edit/task={task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType})
        ) { backStackEntry ->
            // 4. 受け取った文字列からNCMBObjectを復元します
            val obj = 0;
            FormScreen(navController, obj)
        }
    }
}