package com.example.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.json.JSONObject
// 1. NCMBObjectをインポート

@Composable
// 2. Any -> NCMBObjectに変更します
fun DetailScreen(navController: NavController, obj: Any) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("タスク")
                },
                actions = {
                    IconButton(
                        onClick = {
                            val json = JSONObject()
                            // 3. NCMBObjectを文字列化します
                            navController.navigate("edit/task=${json.toString()}")
                        }
                    ){
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(
                        onClick = {
                            // 4. NCMBObjectを削除します
                            navController.navigate("list")
                        }
                    ){
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            )
        },
        content = {
            Column {
                // 5. NCMBObjectのtitleを表示します
                Text(text = "",
                    fontSize = 35.sp,
                    modifier = Modifier.padding(5.dp)
                )
                // 6. NCMBObjectのbodyを表示します
                Text(text = "",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    )
}