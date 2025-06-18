package com.tienmoi.vayonline.nhanh.model.utils

import android.content.Context
import android.content.SharedPreferences
import com.tienmoi.vayonline.nhanh.model.data.TienLoginData
import com.tienmoi.vayonline.nhanh.model.data.TienSystemInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData

object TienSharedPreferencesUtil {
    private const val SP_KEY_DATA = "data"
    private const val TIEN_SYSTEM_INFO_DATA = "tienSystemInfoData"
    private const val USER = "KEY_USER"
    private const val FIELD_CODE = "userFieldCode"
    private const val IS_PRIVACY = "isPrivacy"
    private const val GAID = "gaid"
    private const val CAMPAIGNID = "campaignId"
    private const val PHONE = "phone"
    private const val NAME = "name"
    private const val LICENCE = "licence"
    private const val FCM = "fcm"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SP_KEY_DATA, Context.MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun getUser(): TienLoginData? {
        val userJson = sharedPreferences.getString(USER, "")
        return TienGsonUtil.fromJson(userJson, TienLoginData::class.java)
    }

    fun putTienUser(tienLoginData: TienLoginData) {
        editor.putString(USER, TienGsonUtil.toJson(tienLoginData))
        editor.apply()
    }

    fun clearTienUser() {
        editor.putString(USER, "")
        editor.apply()
    }

    fun getUserFieldCode(): TienUserFieldCodeData? {
        val userJson = sharedPreferences.getString(FIELD_CODE, "")
        return TienGsonUtil.fromJson(userJson, TienUserFieldCodeData::class.java)
    }

    fun putUserFieldCode(userFieldCodeData: TienUserFieldCodeData) {
        editor.putString(FIELD_CODE, TienGsonUtil.toJson(userFieldCodeData))
        editor.apply()
    }


    fun putTienSystemInfoData(tienSystemInfoData: TienSystemInfoData) {
        editor.putString(TIEN_SYSTEM_INFO_DATA, TienGsonUtil.toJson(tienSystemInfoData))
        editor.apply()
    }

    fun getSystemInfoData(): TienSystemInfoData? {
        val userJson = sharedPreferences.getString(TIEN_SYSTEM_INFO_DATA, "")
        return TienGsonUtil.fromJson(userJson, TienSystemInfoData::class.java)
    }

    fun putTienIsFirstInstall(isPrivacy: Boolean) {
        editor.putBoolean(IS_PRIVACY, isPrivacy)
        editor.apply()
    }

    fun isTienFirstInstall(): Boolean {
        return sharedPreferences.getBoolean(IS_PRIVACY, true)
    }

    fun putTienGaId(gaid: String) {
        editor.putString(GAID, gaid)
        editor.apply()
    }

    fun getTienGaId(): String {
        return sharedPreferences.getString(GAID, "") ?: ""
    }


    fun putTienCampaignId(campaignId: String) {
        editor.putString(CAMPAIGNID, campaignId)
        editor.apply()
    }

    fun getTienCampaignId(): String {
        return sharedPreferences.getString(CAMPAIGNID, "") ?: ""
    }

    fun putTienPhone(phone: String) {
        editor.putString(PHONE, phone)
        editor.apply()
    }

    fun getTienPhone(): String {
        return sharedPreferences.getString(PHONE, "") ?: ""
    }

    fun putTienLicence(licence: String) {
        editor.putString(LICENCE, licence)
        editor.apply()
    }

    fun getTienLicence(): String {
        return sharedPreferences.getString(LICENCE, "") ?: ""
    }

    fun putTienName(name: String) {
        editor.putString(NAME, name)
        editor.apply()
    }

    fun getTienName(): String {
        return sharedPreferences.getString(NAME, "") ?: ""
    }

    fun putTienFCM(fcm: String) {
        editor.putString(FCM, fcm)
        editor.apply()
    }

    fun getTienFCM(): String {
        return sharedPreferences.getString(FCM, "") ?: ""
    }

}