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
import com.nifcloud.mbaas.core.NCMBException
import com.nifcloud.mbaas.core.NCMBObject

@Composable
fun FormScreen(navController: NavController, obj: NCMBObject) {
    var objectId = obj.getObjectId()
    var title by remember { mutableStateOf(if (objectId != null) obj.getString("title")!! else "") }
    var body by remember { mutableStateOf(if (objectId != null) obj.getString("body")!! else "") }
    val header = if (title == "") "新規作成" else "編集"
    var progress by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
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

    val save = {
        progress = true
        try {
            obj.put("title", title)
            obj.put("body", body)
            obj.save()
            navController.navigate("list")
        } catch(e: NCMBException){
            Log.d("INFO",  e.message)
            message = "タスクが保存できませんでした"
            showDialog = true
        }
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