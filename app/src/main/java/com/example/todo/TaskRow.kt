package com.example.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
// 1. NCMBObjectを読み込みます
import org.json.JSONObject

@Composable
// 2. Any -> NCMBObjectにします
fun TaskRow(obj: Any, navController: NavController) {
    // 詳細画面に遷移する処理です
    val moveDetail = {
        val json = JSONObject()
        // 3. NCMBObjectを文字列化します
        navController.navigate("detail/task=${json.toString()}")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White, RoundedCornerShape(5.dp))
            .clickable {
                moveDetail()
            }
    ) {
        // 4. NCMBObjectのtitleを表示します
        Text("", fontSize = 25.sp)
    }

}