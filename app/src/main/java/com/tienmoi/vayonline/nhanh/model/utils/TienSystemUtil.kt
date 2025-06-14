package com.tienmoi.vayonline.nhanh.model.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object TienSystemUtil {
    var ORDER_CODE = "orderCode"
    var ONE_VALUE = 1
    var TWO_VALUE = 2
    var REFRESH_EVENT = "refresh"
    var OPERATOR = "operator"
    var TYPE = "type"
    var NAME = "name"
    var VIET = "viet"
    var MOBI = "mobi"
    var VINA = "vina"
    var VIETNAMOBILE = "vietnamobile"
    var SAYMEE = "saymee"
    var OTP = "otp"
    var IS_AUTH = "isAuth"
    var GOOD = "good"
    fun getTienVersionName(context: Context): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionName.toString()
        } catch (e: Exception) {
            ""
        }
    }

    fun getTienPageName(context: Context): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.packageName
        } catch (e: Exception) {
            ""
        }
    }

    fun getTienVersionCode(context: Context): Int {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionCode
        } catch (e: Exception) {
            -1
        }
    }

    fun isTienNumericRegex(str: String?): Boolean {
        if (str.isNullOrEmpty()) return false
        return str.matches("^\\d+$".toRegex())
    }

    fun getTienDateToString(milSecond: String?, pattern: String): String {
        if (milSecond != null && milSecond != "") {
            val date = Date(milSecond.toLong())
            val format = SimpleDateFormat(pattern)
            format.timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
            return format.format(date)
        } else {
            return ""
        }
    }

    fun tienLaunchAppDetail(context: Activity, appPkg: String) {
        try {
            if (TextUtils.isEmpty(appPkg)) return
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=$appPkg")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getTienAndroidId(context: Context): String {
        return Settings.Secure.getString(
            context.applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun addTienComma(str: String): String {
        val myformat: DecimalFormat = DecimalFormat()
        myformat.applyPattern("#,###")
        return myformat.format(str.toDouble())
    }

    private var lastClickTime: Long = 0

    @Synchronized
    fun isFastClick(milliSecond: Int): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < milliSecond) {
            return true
        }
        lastClickTime = time
        return false
    }


}