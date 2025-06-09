package com.tienmoi.vayonline.nhanh.model.utils

import android.util.Log
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import java.util.Enumeration

object TienGsonUtil {
    private fun isEmpty(inStr: String?): Boolean {
        var reTag = false
        if (inStr == null || "" == inStr || "\"null\"" == inStr) {
            reTag = true
        }
        return reTag
    }

    fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        if (isEmpty(json)) {
            return null
        }
        val builder = GsonBuilder()
        val gson = builder.create()
        return try {
            gson.fromJson(json, clazz)
        } catch (ex: Exception) {
            Log.e("GsonUtil", json + " 无法转换为 " + clazz.name + " 对象!", ex)
            null
        }
    }

    fun toJson(target: Any?): String? {
        var jsonStr = toJson(target, null, false, null, "yyyy-MM-dd HH:mm:ss", false)
        if ("{}" == jsonStr) {
            jsonStr = null
        }
        return jsonStr
    }
    fun toJson(
        target: Any?, targetType: Type?,
        isSerializeNulls: Boolean, version: Double?, datePattern: String?,
        excludesFieldsWithoutExpose: Boolean
    ): String? {
        var date = datePattern
        if (target == null) {
            return ""
        }
        val builder = GsonBuilder()
        if (isSerializeNulls) {
            builder.serializeNulls()
        }
        if (version != null) {
            builder.setVersion(version.toDouble())
        }
        if (isEmpty(datePattern)) {
            date = "yyyy-MM-dd HH:mm:ss"
        }
        //builder.setDateFormat(datePattern);
        if (excludesFieldsWithoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation()
        }
        var result = ""
        val gson = builder.create()
        result = try {
            if (targetType != null) {
                gson.toJson(target, targetType)
            } else {
                gson.toJson(target)
            }
        } catch (ex: Exception) {
            if (target is Collection<*> || target is Iterator<*>
                || target is Enumeration<*>
                || target.javaClass.isArray
            ) {
                "[]"
            } else {
                "{}"
            }
        }
        return result
    }
}