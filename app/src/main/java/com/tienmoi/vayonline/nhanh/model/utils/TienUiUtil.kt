package com.tienmoi.vayonline.nhanh.model.utils

import android.app.Activity
import android.util.DisplayMetrics

object TienUiUtil {
    fun getScreenWidthPixels(context: Activity): Int {
        val metric = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }
}