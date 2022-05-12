package com.example.todo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// 1. NCMBExceptionとNCMBObjectを読み込みます

@Composable
// 2. AnyをNCMBObjectに変更します
fun FormScreen(navController: NavController, obj: Any) {
    // 3. objectIdを入れます
    var objectId = null;
    // 4. titleをrememberで定義します。デフォルト値はNCMBObjectのtitleです
    var title = "";
    // 5. bodyをrememberで定義します。デフォルト値はNCMBObjectのbodyです
    var body = "";
    val header = if (title == "") "新規作成" else "編集"
    // 処理実行中のフラグ
    var progress by remember { mutableStateOf(false) }
    // ダイアログ表示/非表示のフラグ
    var showDialog by remember { mutableStateOf(false) }
    // 表示するメッセージ
    var message by remember { mutableStateOf("") }
    // ダイアログを表示する処理
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            buttons = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            title = { Text("タスク保存") },
            text = { Text(message) }
        )
    }

    // 保存処理
    val save = {
        progress = true
        // 6. 保存処理を記述します
        progress = false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("タスクの${header}")
                },
                actions = {
                    IconButton(
                        onClick = {
                        }
                    ){
                        Icon(Icons.Filled.Save, contentDescription = "Save")
                    }
                }
            )
        },
        content = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        Log.d("INFO", it)
                        title = it
                                    },
                    modifier = Modifier.padding(20.dp),
                    label = { Text("タスクのタイトル") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    modifier = Modifier.padding(20.dp).height(150.dp),
                    label = { Text("タスクの本文") },

                    )
                Button(onClick = save,
                    enabled = !progress,
                    modifier = Modifier.padding(20.dp)
                ) {
                    if (progress) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("保存する")
                    }
                }
            }
        }
    )

}