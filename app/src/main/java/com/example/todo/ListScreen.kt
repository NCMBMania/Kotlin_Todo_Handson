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
import com.nifcloud.mbaas.core.NCMBCallback
import com.nifcloud.mbaas.core.NCMBObject
import com.nifcloud.mbaas.core.NCMBQuery

@Composable
fun ListScreen(navController: NavController) {
    var ary = remember { mutableStateOf<List<NCMBObject>>(emptyList()) }
    val query = NCMBQuery.forObject("Task")
    query.findInBackground(NCMBCallback { e, results ->
        if (e == null) {
            ary.value = results as List<NCMBObject>
        }
    })

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
                    items(ary.value) { obj ->
                        TaskRow(obj, navController = navController)
                    }
                }
            }
        }
    )
}