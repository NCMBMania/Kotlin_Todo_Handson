package com.example.todo

import android.util.Log
import com.nifcloud.mbaas.core.NCMBAcl
import com.nifcloud.mbaas.core.NCMBObject
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun strToNCMBObject(str: String, className: String): NCMBObject {
    val json = JSONObject(str)
    val obj = NCMBObject(className = className)
    json.keys().forEach { key ->
        when (key) {
            "objectId" -> {
                obj.setObjectId(json.get(key) as String)
            }
            "createDate" -> {
                obj.setCreateDate(getDate(json.get(key) as String))
            }
            "updateDate" -> {
            } // obj.put("updateDate", getDate(json.get(key) as String))}
            "acl" -> {
                obj.setAcl(getAcl(json.get(key) as JSONObject))
            }
            else -> obj.put(key, json.get(key))
        }
    }
    return obj
}

fun getDate(str: String): Date {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val format = SimpleDateFormat(pattern)
    format.timeZone = SimpleTimeZone(0, "UTC")
    return format.parse(str)
}

fun getAcl(obj: JSONObject): NCMBAcl {
    val acl = NCMBAcl()
    Log.d("INFO", obj.toString())
    obj.keys().forEach { key ->
        val map = obj.get(key) as JSONObject
        when {
            key == "*" -> {
                if (!map.isNull("read") && map.get("read") as Boolean) {
                    acl.publicReadAccess = true
                }
                if (!map.isNull("write") && map.get("write") as Boolean) {
                    acl.publicWriteAccess = true
                }
            }
            key.indexOf("role:") > -1 -> {
                val match = "role:(.*)$".toRegex().find(key)
                val roleName = match?.groups?.get(1)?.value
                if (roleName != null) {
                    if (!map.isNull("read") && map.get("read") as Boolean) {
                        acl.setRoleReadAccess(roleName, true)
                    }
                    if (!map.isNull("write") && map.get("write") as Boolean) {
                        acl.setRoleWriteAccess(roleName, true)
                    }
                }
            }
            else -> {
                if (!map.isNull("read") && map.get("read") as Boolean) {
                    acl.setUserReadAccess(key, true)
                }
                if (!map.isNull("write") && map.get("write") as Boolean) {
                    acl.setUserWriteAccess(key, true)
                }
            }
        }
    }
    return acl
}
