package com.tienmoi.vayonline.nhanh.model.utils

import android.widget.Toast
import com.tienmoi.vayonline.nhanh.app.TienMyApplication.Companion.application

object TienToastUtil {
    fun myToast(text: String) {
        Toast.makeText(application, text, Toast.LENGTH_SHORT).show()
    }
}