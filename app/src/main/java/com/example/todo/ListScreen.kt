package com.example.todo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
// 1. NCMBCallback/NCMBObject/NCMBQueryを読み込みます

@Composable
fun ListScreen(navController: NavController) {
    // 2. aryをrememberのNCMBObjectのListで定義します。デフォルトは空です
    var ary = listOf<Int>()
    // 3. NCMBQueryを定義します。クラス名はTaskです。
    val query = null
    // 4. 検索を実行して、結果をaryに格納します

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("タスク一覧")
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("form")
                        }
                    ){
                        Icon(Icons.Filled.Add, contentDescription = "タスクの作成")
                    }
                }
            )
        },
        content = {
            Column {
                LazyColumn {
                    // 5. ary -> ary.value に書き直してください
                    items(ary) { obj ->
                        TaskRow(obj, navController = navController)
                    }
                }
            }
        }
    )
}