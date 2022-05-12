package com.example.todo

import android.util.Log
// 1. NCMBAcl/NCMBObjectを読み込みます
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

// 2. 文字列をNCMBObjectに復元する関数です

// 文字列をパースして日付にする関数です
fun getDate(str: String): Date {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val format = SimpleDateFormat(pattern)
    format.timeZone = SimpleTimeZone(0, "UTC")
    return format.parse(str)
}

// 3. NCMBAclを復元する関数です
