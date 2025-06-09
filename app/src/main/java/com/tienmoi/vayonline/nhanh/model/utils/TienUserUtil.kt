package com.tienmoi.vayonline.nhanh.model.utils

import android.content.Intent
import com.tienmoi.vayonline.nhanh.app.TienMyApplication
import com.tienmoi.vayonline.nhanh.model.data.TienLoginData
import com.tienmoi.vayonline.nhanh.ui.activity.TienUserLoginActivity

object TienUserUtil {
    private var user: TienLoginData? = null

    fun putTienUser(loginData: TienLoginData) {
        TienSharedPreferencesUtil.putTienUser(loginData)
        user = loginData
    }

    fun getTienUser(): TienLoginData? {
        return if (user == null) {
            user = TienSharedPreferencesUtil.getUser()
            user
        } else {
            user
        }
    }

    fun isTienLogin(): Boolean {
        return getTienUser() != null
    }

    fun tienLogout() {
        user = null
        TienSharedPreferencesUtil.clearTienUser()
        val intent = Intent(TienMyApplication.application, TienUserLoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        TienMyApplication.application.startActivity(intent)
    }
}